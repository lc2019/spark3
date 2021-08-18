package com.atguigu.spark.rdds.practices

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.rdd.RDD

/**
 * @author lzc
 * @date 2021-07-12 17:52
 */
object hot3 {
  def main(args: Array[String]): Unit = {
    val sc: SparkContext = new SparkContext(new SparkConf().setMaster("local[*]").setAppName("wc"))

    val rdd: RDD[String] = sc.textFile("datas/user_visit_action.txt")
    rdd.cache()
    val topIds: Array[String] = top10(rdd)
//        topIds.foreach(println)
    // TODO: 过滤数据
    val frdd: RDD[String] = rdd.filter(
      lines => {
        val datas: Array[String] = lines.split("_")
        if (datas(6) != "-1") {
          topIds.contains(datas(6))
        } else {
          false
        }
      }
    )
    val mrdd: RDD[((String, String), Int)] = frdd.map(
      lines => {
        val datas: Array[String] = lines.split("_")
        // TODO:  (pid sessid) ,count
        ((datas(6), datas(2)), 1)
      }
    ).reduceByKey(_ + _)

    // TODO: 将统计结构进行转换  (pid sessid) ,count =》(pid ，（sessid ,count））
    val srdd: RDD[(String, (String, Int))] = mrdd.map {
      case ((cid, sid), sum) => (cid, (sid, sum))
    }
    // TODO:
    val grdd: RDD[(String, Iterable[(String, Int)])] = srdd.groupByKey()
    val res: RDD[(String, List[(String, Int)])] = grdd.mapValues(
      iter => iter.toList.sortBy(_._2)(Ordering.Int.reverse).take(10)
    )
    res.collect().foreach(println)
    sc.stop()
  }

  /**
   * top 10封装
   *
   * @param rdd
   */
  def top10(rdd: RDD[String]) = {

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
    topN.sortBy(_._2, false).take(10).map(_._1)
  }
}

