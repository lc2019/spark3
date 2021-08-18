package com.atguigu.spark.wordcount

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * @author lzc
 * @date 2021-07-05 23:19
 */
object wordcount3 {
  def main(args: Array[String]): Unit = {
    //准备环境
    val sc = new SparkContext(new SparkConf().setMaster("local").setAppName("wc"))

    //8.停止
    sc.stop()
  }

  def wc1(sc:SparkContext): Unit ={
    val rdd: RDD[String] = sc.makeRDD(List("hello scala", "hello spark"))
    val frdd: RDD[String] = rdd.flatMap((_.split(" ")))
    val grdd: RDD[(String, Iterable[String])] = frdd.groupBy(word => word)
    val mrdd: RDD[(String, Int)] = grdd.mapValues(iter => iter.size)
  }

  def wc2(sc:SparkContext): Unit ={
    val rdd: RDD[String] = sc.makeRDD(List("hello scala", "hello spark"))
    val frdd: RDD[String] = rdd.flatMap((_.split(" ")))
    val mrdd  : RDD[(String, Int)] = frdd.map((_, 1))
    val res: RDD[(String, Int)] = mrdd.groupByKey().mapValues(iter => iter.size)

  }

  // TODO: foldbykey aggregatebykey combinebykey
  def wc3(sc:SparkContext): Unit ={
    val rdd: RDD[String] = sc.makeRDD(List("hello scala", "hello spark"))
    val frdd: RDD[String] = rdd.flatMap((_.split(" ")))
    val mrdd  : RDD[(String, Int)] = frdd.map((_, 1))
    val res: RDD[(String, Int)] = mrdd.reduceByKey(_+_)

  }
  def wc4(sc:SparkContext): Unit ={
    val rdd: RDD[String] = sc.makeRDD(List("hello scala", "hello spark"))
    val frdd: RDD[String] = rdd.flatMap((_.split(" ")))
    val mrdd  : RDD[(String, Int)] = frdd.map((_, 1))
    val res: collection.Map[String, Long] = mrdd.countByKey()
  }
  def wc5(sc:SparkContext): Unit ={
    val rdd: RDD[String] = sc.makeRDD(List("hello scala", "hello spark"))
    val frdd: RDD[String] = rdd.flatMap((_.split(" ")))
    val res: collection.Map[String, Long] = frdd.countByValue()
  }

}
