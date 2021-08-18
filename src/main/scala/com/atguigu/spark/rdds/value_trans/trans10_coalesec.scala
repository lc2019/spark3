package com.atguigu.spark.rdds.value_trans

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * @author lzc
 * @date 2021-07-08 15:13
 */
object trans10_coalesec {
  def main(args: Array[String]): Unit = {
    //准备环境
    val sc = new SparkContext(new SparkConf().setMaster("local[*]").setAppName("rdd"))

    val rdds: RDD[Int] = sc.makeRDD(List(1, 2,3,4,5,6),2)
    //可以选择是否shffle 增加缩减都可以
//    rdds.coalesce(3,true).saveAsTextFile("out2")
    //repartition 底层也是coalesce 但是固定shuffle
    rdds.repartition(3).saveAsTextFile("output")
  }
}
