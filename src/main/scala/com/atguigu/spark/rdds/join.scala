package com.atguigu.spark.rdds

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * @author lzc
 * @date 2021-07-11 14:20
 */
object join {
  def main(args: Array[String]): Unit = {
    val sc = new SparkContext(new SparkConf().setMaster("local[*]").setAppName("rdd"))
    val rdd: RDD[(String, Int)] = sc.makeRDD(List(
      ("a", 1), ("d", 3), ("c", 1))
    )
    val rdd2: RDD[(String, Int)] = sc.makeRDD(List(
      ("a", 3), ("e", 4), ("c", 6))
    )
    //会产生笛卡尔乘积
    rdd.join(rdd2).foreach(println)
    rdd.leftOuterJoin(rdd2).foreach(println)

    //cogroup
    rdd.cogroup(rdd2).foreach(println)

    sc.stop()
  }
}
