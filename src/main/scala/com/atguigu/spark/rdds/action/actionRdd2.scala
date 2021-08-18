package com.atguigu.spark.rdds.action

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * @author lzc
 * @date 2021-07-11 14:20
 */
object actionRdd2 {
  def main(args: Array[String]): Unit = {
    val sc:SparkContext = new SparkContext(new SparkConf().setMaster("local[*]").setAppName("rdd"))
    val rdd: RDD[(String, Int)] = sc.makeRDD(List(("a",1),("a",1),("b",2),("c",3),("b",20)),2)

    val map: collection.Map[String, Long] = rdd.countByKey()
    println(map.mkString(","))

    val map1: collection.Map[(String, Int), Long] = rdd.countByValue()
    println(map1.mkString(","))
    sc.stop()
  }
}
