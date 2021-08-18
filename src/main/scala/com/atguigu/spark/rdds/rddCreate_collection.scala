package com.atguigu.spark.rdds

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * @author lzc
 * @date 2021-07-07 16:17
 */
object rddCreate_collection {
  def main(args: Array[String]): Unit = {
    //准备环境
    val sc = new SparkContext(new SparkConf().setMaster("local[*]").setAppName("rdd"))

    //1.rdd内存创建
    val rds: RDD[Int] = sc.makeRDD(List(1, 2, 3, 4))
    rds.collect().foreach(println)

    //2,文件创建
    val frdd: RDD[String] = sc.textFile("datas")
    frdd.collect().foreach(println)

    sc.stop()
  }
}
