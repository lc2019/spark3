package com.atguigu.spark.rdds.practices

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * @author lzc
 * @date 2021-07-12 14:48
 *       热门品类的统计
 */
object hot1 {
  def main(args: Array[String]): Unit = {
    val sc: SparkContext = new SparkContext(new SparkConf().setMaster("local").setAppName("wc"))

    val rdd: RDD[String] = sc.textFile("datas/user_visit_action.txt")

    //todo  过滤掉不是点击的数据
    val frdd: RDD[String] = rdd.filter(
      lines => {
        val words: Array[String] = lines.split("_")
        words(6) != "-1"
      }
    )
    val mrdd: RDD[(String, Int)] = frdd.map {
      lines => {
        val words: Array[String] = lines.split("_")
        (words(6), 1)
      }
    }

    val clickData: RDD[(String, Int)] = mrdd.reduceByKey(_ + _)
    // TODO: 订单数据
    val odr: RDD[String] = rdd.filter {
      lines => {
        val words: Array[String] = lines.split("_")
        words(8) != "null"
      }
    }

    val order: RDD[(String, Int)] = odr.flatMap {
      lines => {
        val words: Array[String] = lines.split("_")
        val ids: Array[String] = words(8).split(",")
        ids.map(
          id => (id, 1)
        )
      }
    }

    val orderData: RDD[(String, Int)] = order.reduceByKey(_ + _)

    // TODO: 支付数据
    val pdr: RDD[String] = rdd.filter {
      lines => {
        val words: Array[String] = lines.split("_")
        words(10) != "null"
      }
    }

    val pay: RDD[(String, Int)] = pdr.flatMap {
      lines => {
        val words: Array[String] = lines.split("_")
        val ids: Array[String] = words(10).split(",")
        ids.map(
          id => (id, 1)
        )
      }
    }

    val payData: RDD[(String, Int)] = pay.reduceByKey(_ + _)

    // TODO: 结构转换
    val cclick: RDD[(String, (Int, Int, Int))] = clickData.map {
      case (cid, clk) => {
        (cid, (clk, 0, 0))
      }
    }

    val oclick: RDD[(String, (Int, Int, Int))] = orderData.map {
      case (oid, olk) => {
        (oid, (0, olk,0))
      }
    }
    val pclick: RDD[(String, (Int, Int, Int))] = payData.map {
      case (pid, plk) => {
        (pid, (0, 0, plk))
      }
    }


    // TODO: union
    val total: RDD[(String, (Int, Int, Int))] = cclick.union(oclick).union(pclick)

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