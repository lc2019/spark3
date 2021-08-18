package com.atguigu.spark.rdds.practices

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * @author lzc
 * @date 2021-07-12 14:48
 *       热门品类的统计
 */
object hot2 {
  def main(args: Array[String]): Unit = {
    val sc: SparkContext = new SparkContext(new SparkConf().setMaster("local").setAppName("wc"))

    val rdd: RDD[String] = sc.textFile("datas/user_visit_action.txt")

    // TODO: flatmap返回可迭代的集合
    val total: RDD[(String, (Int, Int, Int))] = rdd.flatMap(
      action => {
        val datas: Array[String] = action.split("_")
        if (datas(6) != "-1") {
          List((datas(6), (1, 0, 0)))
        } else if (datas(8) != "null") {
          val data: Array[String] = datas(8).split(",")
          data.map {
            id => (id, 1)
          }
          List((datas(8), (0, 1, 0)))
        } else if (datas(10) != "null") {
          val data: Array[String] = datas(10).split(",")
          data.map {
            id => (id, 1)
          }
          List((datas(10), (0, 0, 1)))
        } else {
          Nil
        }
      }
    )

    val topN: RDD[(String, (Int, Int, Int))] = total.reduceByKey(
      (t1, t2) => {
        (t1._1 + t2._1, t1._2 + t2._2, t1._3 + t2._3)
      }
    )
    val top10: Array[(String, (Int, Int, Int))] = topN.sortBy(_._2, false).take(10)
    top10.foreach(println)

    sc.stop()
  }
}