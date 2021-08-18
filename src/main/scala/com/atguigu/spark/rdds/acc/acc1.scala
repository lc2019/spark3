package com.atguigu.spark.rdds.acc

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * @author lzc
 * @date 2021-07-12 11:58
 */
object acc1 {
  def main(args: Array[String]): Unit = {
    val sc = new SparkContext(new SparkConf().setMaster("local[*]").setAppName("rdd"))
    val rdd: RDD[Int] = sc.makeRDD(List(1, 2, 3, 4, 5, 6), 2)

    var sum = 0
    rdd.foreach(
      //executor执行逻辑
      num => {
        sum = num + sum
      }
    )
    // TODO:  driver执行 没有返回sum的结果 sum不变
    println(sum)
  }
}
