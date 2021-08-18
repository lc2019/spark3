package com.atguigu.spark.rdds.practices

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * @author lzc
 * @date 2021-07-11 17:13
 */
object practice {
  def main(args: Array[String]): Unit = {
    //todo 1.读取文件
    val sc = new SparkContext(new SparkConf().setMaster("local[*]").setAppName("topn"))
    val rdd: RDD[String] = sc.textFile("datas/agent.log")

    // TODO: 2.转换结构 分组
    //1516609143867 6 7 64 16
    val mrdd: RDD[((String, String), Int)] = rdd.map(
      lines => {
        val datas: Array[String] = lines.split(" ")
        // (pro,ad) ,1)
        ((datas(1), datas(4)), 1)
      }
    )
    // TODO: 3.聚合
    val rrdd: RDD[((String, String), Int)] = mrdd.reduceByKey(_ + _)

    // TODO: 4.数据结构转换  // (pro,(ad ,sum))
    val proads: RDD[(String, (String, Int))] = rrdd.map {
      case (key, sum) => (key._1, (key._2, sum))
    }

    // TODO: 5.分组 (pro,(ad ,sum))
    val grdd: RDD[(String, Iterable[(String, Int)])] = proads.groupByKey()
    //todo 6.排序取前3 (pro,(ad ,sum),(ad ,sum),(ad ,sum),(ad ,sum))
    val res: RDD[(String, List[(String, Int)])] = grdd.mapValues(
      //      (iter: Iterable[(String, Int)]) => {
      //        iter.toList.sortWith(
      //          (l: (String, Int), r: (String, Int)) => {
      //            //倒序
      //            l._2 > r._2
      //          }
      //        )
      //      }.take(3)
      iter => {
        iter.toList.sortBy(_._2)(Ordering.Int.reverse).take(3)
      }
    )

    res.collect().foreach(println)
  }
}
