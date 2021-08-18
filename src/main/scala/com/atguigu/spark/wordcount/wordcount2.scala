package com.atguigu.spark.wordcount

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * @author lzc
 * @date 2021-07-05 23:19
 */
object wordcount2 {
  def main(args: Array[String]): Unit = {
    //准备环境
    val sc = new SparkContext(new SparkConf().setMaster("local").setAppName("wc"))
    //2.读取文件
    val words: RDD[String] = sc.textFile("datas")
    //3.切分行
    val lines: RDD[String] = words.flatMap(_.split(" "))
    //4.分组 针对每一个元素
    val rdd = lines.map((_, 1)).reduceByKey(_ + _)
    //6.收集结果
    val res: Array[(String, Int)] = rdd.collect()
    //7.打印
    res.foreach(println)
    //8.停止
    sc.stop()
  }
}
