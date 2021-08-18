package com.atguigu.spark.rdds.key_value

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * @author lzc
 * @date 2021年7月11日 13点22分
 */
object combinebykey {
  def main(args: Array[String]): Unit = {
    //准备环境
    val sc = new SparkContext(new SparkConf().setMaster("local[*]").setAppName("rdd"))

    val rdd: RDD[(String, Int)] = sc.makeRDD(List(
      ("a", 2), ("b", 2), ("a", 4), ("b", 6)), 2
    )

    /**
     * 初始值
     * 分区内计算规则
     * 分区间计算规则
     */
    val res: RDD[(String, (Int, Int))] = rdd.combineByKey(
      //第一个处理的数据个格式  (("a", 1)) ==>("a",(1,1)) 继续相加 ("a", 1)
      // TODO: 分区内 分区间的类型需要显示声明
      num => (num, 1),
      // TODO: 分区内
      (x: (Int, Int), y: Int) => {
        //值相加  第二个是计数
        (x._1 + y, x._2 + 1)
      },
      // TODO:  分区间
      (x: (Int, Int), y: (Int, Int)) => {
        (x._1 + y._1, x._2 + y._2)
      }
    )
    res.collect().foreach(println)
  }
}
