package com.atguigu.spark.rdds.value_trans

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

import java.text.SimpleDateFormat
import java.util.Date

/**
 * @author lzc
 * @date 2021-07-08 15:13
 */
object trans7_filter {
  def main(args: Array[String]): Unit = {
    //准备环境
    val sc = new SparkContext(new SparkConf().setMaster("local[*]").setAppName("rdd"))
    val frdd: RDD[String] = sc.textFile("datas/apache.log")

    val firdd: RDD[String] = frdd.filter(
      lines => {
        val date: Array[String] = lines.split(" ")
        val time: String = date(3)
        time.startsWith("17/05/2015")
      }
    )
    firdd.collect().foreach(println)
  }
}
