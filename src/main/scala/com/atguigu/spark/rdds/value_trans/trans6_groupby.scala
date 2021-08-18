package com.atguigu.spark.rdds.value_trans

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * @author lzc
 * @date 2021-07-08 15:13
 */
object trans6_groupby {
  def main(args: Array[String]): Unit = {
    //准备环境
    val sc = new SparkContext(new SparkConf().setMaster("local[*]").setAppName("rdd"))

    val rdd: RDD[String] =sc.makeRDD(List("hello","hadoop","spark","scala"),2)
    //使用手写字母分组
    val grdd: RDD[(Char, Iterable[String])] = rdd.groupBy(_.charAt(0))

    grdd.collect().foreach(println)
  }
}
