package com.atguigu.spark.rdds.key_value

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * @author lzc
 * @date 2021-07-08 15:13
 */
object aggregatebykey {
  def main(args: Array[String]): Unit = {
    //准备环境
    val sc = new SparkContext(new SparkConf().setMaster("local[*]").setAppName("rdd"))

    val rdd: RDD[(String, Int)] = sc.makeRDD(List(
      ("a", 1), ("a", 2), ("a", 3),("b", 1)),2
    )

    /**
     * 初始值
     * 分区内计算规则
     * 分区间计算规则
     */
    val res: RDD[(String, Int)] = rdd.aggregateByKey(0)(
      (x: Int, y: Int) => math.max(x, y),
      (x: Int, y: Int) => x + y
    )
    res.collect().foreach(println)
  }
}
