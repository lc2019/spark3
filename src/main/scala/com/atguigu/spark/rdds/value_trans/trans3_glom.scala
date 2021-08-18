package com.atguigu.spark.rdds.value_trans

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * @author lzc
 * @date 2021-07-08 15:13
 */
object trans3_glom {
  def main(args: Array[String]): Unit = {
    //准备环境
    val sc = new SparkContext(new SparkConf().setMaster("local[*]").setAppName("rdd"))

    val rdd: RDD[Int] = sc.makeRDD(List(1, 2, 3, 4),2)

    val grdd: RDD[Array[Int]] = rdd.glom()
    //取每个分区最大值求和
    val mrdd: RDD[Int] = grdd.map(
      arr => {
        arr.max
      }
    )

    println(mrdd.collect().sum)
  }
}
