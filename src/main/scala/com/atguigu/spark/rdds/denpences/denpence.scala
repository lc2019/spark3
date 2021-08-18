package com.atguigu.spark.rdds.denpences

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * @author lzc
 * @date 2021-07-12 09:19
 *       依赖关系 分区的依赖关系
 */
object denpence {
  def main(args: Array[String]): Unit = {
    //准备环境
    val sc = new SparkContext(new SparkConf().setMaster("local").setAppName("wc"))
    //2.读取文件
    val words: RDD[String] = sc.textFile("datas/1.txt")
    println(words.dependencies)
    //3.切分行
    val lines: RDD[String] = words.flatMap(_.split(" "))
    println(lines.dependencies)
    //4.分组
    val rdd = lines.map((_, 1)).reduceByKey(_ + _)
    println(rdd.dependencies)
    //6.收集结果
    val res: Array[(String, Int)] = rdd.collect()
    //7.打印
    res.foreach(println)
    //8.停止
    sc.stop()
  }
}
