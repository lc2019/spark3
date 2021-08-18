package com.atguigu.spark.rdds.acc

import org.apache.spark.broadcast.Broadcast
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.rdd.RDD

/**
 * @author lzc
 * @date 2021-07-12 14:00
 */
object bc {
  def main(args: Array[String]): Unit = {
    val sc = new SparkContext(new SparkConf().setMaster("local[*]").setAppName("rdd"))
    val rdd1: RDD[(String, Int)] = sc.makeRDD(List(("a", 1), ("b", 2)), 2)
    val list1: List[(String, Int)] = List(("a", 3), ("b", 4))
    // TODO: 封装广播变量  解决多个task的重复数据问题
    val bc: Broadcast[List[(String, Int)]] = sc.broadcast(list1)
    val res: RDD[(String, (Int, Int))] = rdd1.map {
      case (k, cnt) => {
        var tmp = 0
        bc.value.foreach {
          case (k1, i) => {
            if (k == k1) {
              tmp = i
            }
          }
        }
        //(a,(1,3)),(b,(2,4))
        (k, (cnt, tmp))
      }
    }
    println(res.collect().mkString(","))

  }
}
