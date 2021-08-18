package com.atguigu.spark.rdds.action

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * @author lzc
 * @date 2021-07-11 14:20
 */
object actionRdd {
  def main(args: Array[String]): Unit = {
    val sc:SparkContext = new SparkContext(new SparkConf().setMaster("local[*]").setAppName("rdd"))
    val rdd: RDD[Int] = sc.makeRDD(List(1,2,3,4),2)
    val arr: Array[Int] = rdd.collect()

    println(arr.toBuffer)

    val i: Int = rdd.reduce(_ + _)
    println(i)

    val count: Long = rdd.count()
    println(count)

    println(rdd.first())

    println(rdd.take(2).mkString(","))

    //行动算子  初始值在分区间也会参与计算 40 当分区内和分区间的算法规则一致 使用fold代替
    val ardd: Int = rdd.aggregate(10)(_ + _, _ + _)
    val frdd: Int = rdd.fold(10)(_ + _)
    println(ardd,frdd)
    sc.stop()
  }
}
