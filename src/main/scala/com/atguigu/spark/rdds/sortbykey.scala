package com.atguigu.spark.rdds

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.rdd.RDD

/**
 * @author lzc
 * @date 2021-07-11 14:20
 */
object sortbykey {
  def main(args: Array[String]): Unit = {
    val sc = new SparkContext(new SparkConf().setMaster("local[*]").setAppName("rdd"))
    val rdd: RDD[(String, Int)] = sc.makeRDD(List(
      ("a", 1), ("b", 3), ("b", 1))
    )


    rdd.sortByKey(true).collect().foreach(println)
  }
}
