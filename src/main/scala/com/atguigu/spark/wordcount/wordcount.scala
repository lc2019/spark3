package com.atguigu.spark.wordcount

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * @author lzc
 * @date 2021-07-05 23:19
 */
object wordcount {
  def main(args: Array[String]): Unit = {
    //准备环境
    val sc = new SparkContext(new SparkConf().setMaster("local[*]").setAppName("wc"))
    //2.读取文件
    val words: RDD[String] = sc.textFile("datas/word.txt")
    //3.切分行
    val lines: RDD[String] = words.flatMap(_.split(" "))
    //4.分组
    val wg: RDD[(String, Iterable[String])] = lines.groupBy(word => word)
    //5.分组后的数据类型转行聚合
    //    val rmap = wg.map {
    //      case (word, list) => (word, list.size)
    //    }
    val rmap: RDD[(String, Int)] = wg.mapValues(
      list => list.size
    )
    //6.收集结果
    val res: Array[(String, Int)] = rmap.collect()
    //7.打印
    res.foreach(println)
    //8.停止
    sc.stop()
  }
}
