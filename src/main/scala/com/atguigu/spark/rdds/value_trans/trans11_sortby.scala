package com.atguigu.spark.rdds.value_trans

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * @author lzc
 * @date 2021-07-08 15:13
 */
object trans11_sortby {
  def main(args: Array[String]): Unit = {
    //准备环境
    val sc = new SparkContext(new SparkConf().setMaster("local[*]").setAppName("rdd"))

    val rdds: RDD[(String, Int)] = sc.makeRDD(List(("1", 2), ("11", 2), ("3", 3)), 2)
    rdds.sortBy(t=>t._1.toInt).collect().foreach(println)
  }
}
