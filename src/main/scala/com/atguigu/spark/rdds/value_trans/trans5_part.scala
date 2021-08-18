package com.atguigu.spark.rdds.value_trans

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * @author lzc
 * @date 2021-07-08 15:13
 */
object trans5_part {
  def main(args: Array[String]): Unit = {
    //准备环境
    val sc = new SparkContext(new SparkConf().setMaster("local[*]").setAppName("rdd"))

    val rdd: RDD[Int] = sc.makeRDD(List(1, 2, 3, 4),2)
    //map不会改变原来的分区的数据的分布
    val mrdd: RDD[Int] = rdd.map(_ * 2)
    println(mrdd.collect().toBuffer)
  }
}
