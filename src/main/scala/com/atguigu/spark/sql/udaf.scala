package com.atguigu.spark.sql

import org.apache.spark.SparkConf
import org.apache.spark.sql.{DataFrame, Encoder, Encoders, SparkSession, functions}
import org.apache.spark.sql.expressions.Aggregator

/**
 * @author lzc
 * @date 2021-07-12 22:30
 */
object udaf {
  def main(args: Array[String]): Unit = {
    val ss: SparkSession = new SparkSession.Builder().config(new SparkConf().setMaster("local[*]").setAppName("sql")).getOrCreate()

    val df: DataFrame = ss.read.json("datas/user.json")
    ss.udf.register("ageAvg", functions.udaf(new avg()))
    df.createOrReplaceTempView("user")
    ss.sql("select ageAvg(age) from user").show()
  }

  case class avgbuff(var total: Long, var count: Long)

  /**
   * 类型参数：
   * IN – 聚合的输入类型。
   * BUF – 减少的中间值的类型。
   * OUT – 最终输出结果的类型。
   */
  class avg extends Aggregator[Long, avgbuff, Long] {
    override def zero: avgbuff = avgbuff(0L, 0L)

    // TODO: 单个计算
    override def reduce(buff: avgbuff, age: Long): avgbuff = {
      //      buff.total = buff.total + age
      //      buff.count = buff.count + 1
      buff.total += age
      buff.count += 1
      buff
    }

    // TODO: 分区间聚合
    override def merge(b1: avgbuff, b2: avgbuff): avgbuff = {
      b1.total += b2.total
      b1.count += b2.count
      b1
    }

    // TODO: 完成计算
    override def finish(buff: avgbuff): Long = {
      buff.total / buff.count
    }

    override def bufferEncoder: Encoder[avgbuff] = Encoders.product

    override def outputEncoder: Encoder[Long] = Encoders.scalaLong
  }

}
