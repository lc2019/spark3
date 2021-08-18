package com.atguigu.spark.rdds.key_value

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * @author lzc
 * @date 2021-07-08 15:13
 */
object groupbykey {
  def main(args: Array[String]): Unit = {
    //准备环境
    val sc = new SparkContext(new SparkConf().setMaster("local[*]").setAppName("rdd"))

    val rdd: RDD[(String, Int)] = sc.makeRDD(List(
      ("a", 1), ("a", 2), ("b", 1))
    )
    //key v的集合的元组
    val grdd: RDD[(String, Iterable[Int])] = rdd.groupByKey()
    val res: RDD[(String, Int)] = grdd.map {
          //模式匹配 对集合进行求和
      case (s, iter) => (s, iter.sum)
    }
    res.collect().foreach(println)
  }
}
