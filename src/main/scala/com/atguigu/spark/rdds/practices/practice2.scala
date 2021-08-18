package com.atguigu.spark.rdds.practices

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * @author lzc
 * @date 2021-07-11 17:13
 */
object practice2 {
  def main(args: Array[String]): Unit = {
    //todo 1.读取文件
    val sc = new SparkContext(new SparkConf().setMaster("local[*]").setAppName("topn"))
    val rdd: RDD[String] = sc.textFile("datas/agent.log")

  }
}
