package com.atguigu.spark.rdds.key_value

import org.apache.spark.rdd.RDD
import org.apache.spark.{HashPartitioner, SparkConf, SparkContext}

/**
 * @author lzc
 * @date 2021-07-11 12:12
 */
object partitionby {
  def main(args: Array[String]): Unit = {
    val sc = new SparkContext(new SparkConf().setMaster("local[*]").setAppName("rdd"))

    val rdd: RDD[(String, Int)] = sc.makeRDD(List(
      ("a", 1), ("b", 2), ("c", 3), ("a", 4)
    ), 2)
    //todo 取余运算
    /**
     * def getPartition(key: Any): Int = key match {
     * case null => 0
     * case _ => Utils.nonNegativeMod(key.hashCode, numPartitions)
     * }
     *
     * override def equals(other: Any): Boolean = other match {
     * case h: HashPartitioner =>
     * h.numPartitions == numPartitions
     * case _ => false
     */
    val pdd: RDD[(String, Int)] = rdd.partitionBy(new HashPartitioner(3))

  }
}
