package com.atguigu.spark.rdds.value_value

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.rdd.RDD

/**
 * @author lzc
 * @date 2021-07-08 16:48
 */
object vv {
  def main(args: Array[String]): Unit = {
    //2个数据原的操作
    val sc = new SparkContext(new SparkConf().setMaster("local[*]").setAppName("rdd"))

    val rdd1: RDD[Int] = sc.makeRDD(List(1, 2, 3, 4))
    val rdd2: RDD[Int] = sc.makeRDD(List(3, 4, 5, 6))

    //并集
    val rdd3: RDD[Int] = rdd1.intersection(rdd2)
    println(rdd3.collect().mkString(","))
    //合集
    val rdd4: RDD[Int] = rdd1.union(rdd2)
    println(rdd4.collect().mkString(","))
    //差集
    val rdd5: RDD[Int] = rdd1.subtract(rdd2)
    println(rdd5.collect().mkString(","))
    //拉链 两个 RDD 具有*相同数量的分区*和*相同数量的元素在每个分区*
      val rdd6: RDD[(Int, Int)] = rdd1.zip(rdd2)
    println(rdd6.collect().mkString(","))
  }
}
