package com.jld.day01.wc

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * @author lzc
 * @date 2022-05-14 9:34
 * world count demo 通用模式
 */
object wc {
  def main(args: Array[String]): Unit = {
    //准备环境
    val conf: SparkConf = new SparkConf()
    //运行模式
    conf.setMaster("local[*]")
    //指定运行job的名称
    conf.setAppName("worldcount")
    //程序的入口
    val sc: SparkContext = new SparkContext(conf)

    //读取文件-rdd
    val rdd1: RDD[String] = sc.textFile("datas/1.txt")
    rdd1.flatMap(_.split(" ")).map((_,1)).reduceByKey(_+_).foreach(println)

    //关闭sparkContext
    sc.stop()
  }
}
