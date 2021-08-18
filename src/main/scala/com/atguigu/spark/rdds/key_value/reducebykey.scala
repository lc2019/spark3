package com.atguigu.spark.rdds.key_value

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * @author lzc
 * @date 2021-07-08 21:53
 */
object reducebykey {
  def main(args: Array[String]): Unit = {
    //准备环境
    val sc  = new SparkContext(new SparkConf().setMaster("local[*]").setAppName("rdd"))
    val rdd: RDD[(String, Int)] = sc.makeRDD(List(
      ("a", 1), ("a", 2), ("b", 1))
    )
    val res: RDD[(String, Int)] = rdd.reduceByKey(_ + _)
    res.collect().foreach(println)
    }
}
