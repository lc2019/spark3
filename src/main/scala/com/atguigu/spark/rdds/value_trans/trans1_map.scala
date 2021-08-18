package com.atguigu.spark.rdds.value_trans

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * @author lzc
 * @date 2021-07-07 21:40
 */
object trans1_map {
  def main(args: Array[String]): Unit = {
    //准备环境
    val sc = new SparkContext(new SparkConf().setMaster("local[*]").setAppName("rdd"))

//    val rdd: RDD[Int] = sc.makeRDD(List(1, 2, 3, 4))

    //    rdd.trans1_map((num:Int)=>{num*2})
    //    //代码逻辑只有1行{}省略
    //    rdd.trans1_map((num:Int)=>num*2)
    //    //类型可以推断出来 类型省略
    //    rdd.trans1_map((num)=>num*2)
    //    //参数只有1个可以省略
    //    rdd.trans1_map(num=>num*2)
    //    //参数只有1个按顺序出现
//    val mrdd: RDD[Int] = rdd.trans1_map(_ * 2)
    val frdd: RDD[String] = sc.textFile("datas/apache.log")
    //map里面封装的是处理的逻辑 函数
    val mrdd: RDD[String] = frdd.map(lines => {
      val datas: Array[String] = lines.split(" ")
      datas(6)
    })
    mrdd.collect().foreach(println)

    sc.stop()
  }
}
