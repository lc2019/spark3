package com.atguigu.spark.rdds.partition

import org.apache.spark.{Partitioner, SparkConf, SparkContext}
import org.apache.spark.rdd.RDD

/**
 * @author lzc
 * @date 2021-07-12 11:17
 */
object part1 {
  def main(args: Array[String]): Unit = {
    //准备环境
    val sc = new SparkContext(new SparkConf().setMaster("local").setAppName("wc"))
    sc.setCheckpointDir("ck")
    //2.读取文件
    val words: RDD[(String, String)] = sc.makeRDD(List(
      ("nba","eee"),
      ("cba","333"),
      ("nba","222"),
      ("wba","1111"),
    ))

    val res: RDD[(String, String)] = words.partitionBy(new myPartitioner(3))

    res.saveAsTextFile("out")
  }

  /**
   * class HashPartitioner(partitions: Int) extends Partitioner {
   * require(partitions >= 0, s"Number of partitions ($partitions) cannot be negative.")
   *
   * def numPartitions: Int = partitions
   *
   * def getPartition(key: Any): Int = key match {
   * case null => 0
   * case _ => Utils.nonNegativeMod(key.hashCode, numPartitions)
   * }
   *
   * @param num
   */
  class myPartitioner(num: Int) extends Partitioner {
    override def numPartitions: Int = num

    override def getPartition(key: Any): Int = key match {
      case "cba" => 0
      case "nba" => 1
      case _ => 2
    }
  }
}
