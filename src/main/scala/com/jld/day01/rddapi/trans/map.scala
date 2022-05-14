package com.jld.day01.rddapi.trans

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

//样例类
case class Student(id: Int, name: String, gender: String, age: Int, addr: String)

/**
 * @author lzc
 * @date 2022-05-14 18:42
 */
object map {
  def main(args: Array[String]): Unit = {
    val conf: SparkConf = new SparkConf()
    conf.setMaster("local")
    conf.setAppName("rdd")

    val sc = new SparkContext(conf)

    //map 每个元素对应一个函数
    val list = List("hello lc", "hello 2020")
    val rdd: RDD[String] = sc.makeRDD(list)
    //转换
    val rdd2: RDD[String] = rdd.map(s => s.toUpperCase)
    rdd2.foreach(println)

    val list2 = List("hello", "abc", "bcd")
    val rdd3: RDD[String] = sc.makeRDD(list2)

    val res: RDD[Any] = rdd3.map(s => {
      if (s.startsWith("a")) (s, 1)
      else if
      (s.startsWith("b")) (s, 2)
      else
        (s, 3)
    })
    res.foreach(println)

    val list1: List[Any] = List(
      "1, zhangsan, femal, 28, shanghai",
      "2, lisi, femal, 28, beijing",
      "3, wangwu, male, 28, xian"
    )

    val rdd4: RDD[String] = sc.makeRDD(list1)

    rdd4.map(s => {
      val ss: Array[String] = s.split(",")
      Student(ss(0).toInt, ss(1), ss(2), ss(3).toInt, ss(4))
    })

    sc.stop()
  }
}
