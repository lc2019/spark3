package com.atguigu.spark.rdds.key_value

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * @author lzc
 * @date 2021-07-11 13:23
 */
object foldbykey {
  def main(args: Array[String]): Unit = {
    val sc = new SparkContext(new SparkConf().setMaster("local[*]").setAppName("rdd"))

    val rdd: RDD[(String, Int)] = sc.makeRDD(List(
      ("a", 1), ("b", 2), ("b", 3),
      ("a", 2), ("b", 1), ("b", 13)
    ), 2)
    //外部数据源进行运算 分区内和分区间的计算规则相同，如果要实现分区内和分区间的不同可以使用aggregatebykey
    val res: RDD[(String, Int)] = rdd.foldByKey(0)(_ + _)
    res.collect().foreach(println)
  }
}
