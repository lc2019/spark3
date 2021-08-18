package com.atguigu.spark.rdds.value_trans

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * @author lzc
 * @date 2021-07-07 21:40
 */
object trans2_flatmap {
  def main(args: Array[String]): Unit = {
    //准备环境
    val sc = new SparkContext(new SparkConf().setMaster("local[*]").setAppName("rdd"))
//    val srdd: RDD[String] = sc.makeRDD(List("hello spark", "hello scala"))
//    //扁平化操作
//    val mrdd: RDD[String] = srdd.flatMap(
//      //要求返回可迭代的集合
//      ss => {
//        ss.split(" ")
//      }
//    )

    val srdd: RDD[Any] = sc.makeRDD(List(List(1, 2), 3, List(4, 5)))
    val mrdd: RDD[Any] = srdd.flatMap {
      case list: List[_] => list
      case dat => List(dat)
    }
    mrdd.collect().foreach(println)

    sc.stop()
  }
}
