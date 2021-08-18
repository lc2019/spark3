package com.atguigu.spark.rdds

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * @author lzc
 * @date 2021-07-07 16:17
 */
object rddCreate_file {
  def main(args: Array[String]): Unit = {
    //准备环境
    val sc = new SparkContext(new SparkConf().setMaster("local[*]").setAppName("rdd"))

    //数据来自那个文件 元组---文件路径，文件内容
    sc.wholeTextFiles("datas/word.txt").collect().foreach(println)
    val frdd: RDD[String] = sc.textFile("datas/1.txt")
    frdd.collect().foreach(println)
    //默认分区是cpu核数
    val srdd: RDD[Int] = sc.makeRDD(List(1, 2, 3, 4), 2)
    srdd.collect().foreach(println)
    sc.stop()
  }
}
