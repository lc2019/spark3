package com.jld.day01

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * @author lzc
 * @date 2022-05-14 18:35
 */
object cacherdd {
  def main(args: Array[String]): Unit = {

    val conf: SparkConf = new SparkConf()
    conf.setMaster("local")
    conf.setAppName("rdd")

    val sc = new SparkContext(conf)

    val ints: List[Int] = List(1, 2, 3, 4)
    //从内存得到rdd
    val rdd1: RDD[Int] = sc.makeRDD(ints)
//    val rdd: RDD[Int] = sc.parallelize(ints)


    rdd1.foreach(println)

    sc.stop()
  }
}
