package com.atguigu.spark.rdds.persist

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * @author lzc
 * @date 2021-07-12 09:19
 *       持久化
 */
object cache {
  def main(args: Array[String]): Unit = {
    //准备环境
    val sc = new SparkContext(new SparkConf().setMaster("local").setAppName("wc"))
    sc.setCheckpointDir("ck")
    //2.读取文件
    val words: RDD[String] = sc.textFile("datas/1.txt")
    //3.切分行
    val lines: RDD[String] = words.flatMap(str => {
      println("yyyyyy")
      str.split(" ")
    })

    val mrdd: RDD[(String, Int)] = lines.map(
      lines => {
        println("xxxxxxxxxxx")
        (lines, 1)
      }
    )
    // TODO: 1.内存 2，磁盘 3，堆外
    mrdd.cache()
    mrdd.checkpoint()
    //    mrdd.persist(StorageLevel.DISK_ONLY)
    // TODO:  rdd不保存数据 rdd对象可以重复使用 但是数据无法重复使用
    val grdd: RDD[(String, Iterable[Int])] = mrdd.groupByKey()
    val rdd: RDD[(String, Int)] = mrdd.reduceByKey(_ + _)

    grdd.collect().foreach(println)
    println("==============")
    rdd.collect().foreach(println)
    //8.停止
    sc.stop()
  }
}
