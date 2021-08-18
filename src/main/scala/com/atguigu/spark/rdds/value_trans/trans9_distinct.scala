package com.atguigu.spark.rdds.value_trans

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * @author lzc
 * @date 2021-07-08 15:13
 */
object trans9_distinct {
  def main(args: Array[String]): Unit = {
    //准备环境
    val sc = new SparkContext(new SparkConf().setMaster("local[*]").setAppName("rdd"))

    val rdds: RDD[Int] = sc.makeRDD(List(1, 2, 2, 4, 3, 6, 3, 4, 9, 10))
    //  case _ => map(x => (x, null)).reduceByKey((x, _) => x, numPartitions).map(_._1)
    rdds.distinct().collect().foreach(println)
  }
}
