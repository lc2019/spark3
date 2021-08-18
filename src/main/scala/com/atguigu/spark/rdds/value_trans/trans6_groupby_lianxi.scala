package com.atguigu.spark.rdds.value_trans

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

import java.text.SimpleDateFormat
import java.util.Date

/**
 * @author lzc
 * @date 2021-07-08 15:13
 */
object trans6_groupby_lianxi {
  def main(args: Array[String]): Unit = {
    //准备环境
    val sc = new SparkContext(new SparkConf().setMaster("local[*]").setAppName("rdd"))
    val frdd: RDD[String] = sc.textFile("datas/apache.log")

    val res: RDD[(Int, Iterable[(Int, Int)])] = frdd.map(
      words => {
        val ss: Array[String] = words.split(" ")
        val date: String = ss(3)
        val sdf = new SimpleDateFormat("dd/MM/yyyy:HH:mm:ss")
        val dates: Date = sdf.parse(date)
        val hours: Int = dates.getHours
        (hours, 1)
      }
    ).groupBy(_._1) //使用hour分组
    val res2: RDD[(Int, Int)] = res.map {
          //模式匹配
      case (hours, iter) => (hours, iter.size)
    }

    res2.collect().foreach(println)
  }
}
