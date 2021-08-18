package com.atguigu.spark.rdds.acc

import org.apache.spark.rdd.RDD
import org.apache.spark.util.{AccumulatorV2, LongAccumulator}
import org.apache.spark.{SparkConf, SparkContext}

import scala.collection.mutable


/**
 * @author lzc
 * @date 2021-07-12 11:58
 *       累加器 --可以将executor端累加的数据结果传递给driver
 */
object acc3 {
  def main(args: Array[String]): Unit = {
    val sc = new SparkContext(new SparkConf().setMaster("local[*]").setAppName("rdd"))
    val rdd: RDD[String] = sc.makeRDD(List("hello spark","hello scala"), 2)

    // TODO:  1.创建累加器
    val acc = new wcAcc
    // TODO: 2.注册累加器
    sc.register(acc,"wordcount")
    //todo 3.使用累加器
    rdd.foreach(
       lines =>{
         val words: Array[String] = lines.split(" ")
         words.foreach (
           word => {acc.add(word)}
         )
       }
    )
    println(acc.value)
    sc.stop()
  }

  // TODO:    1.继承AccumulatorV2
  //          2.定义输入输出的类型
  //          3.重写方法
  class wcAcc extends AccumulatorV2[String, mutable.Map[String, Int]] {
    // TODO: 自定义输出的map 是一个特质 使用（）调用伴生对象apply的方法
    private val wcMap = mutable.Map[String, Int]()

    //初始化
    override def isZero: Boolean = {
      wcMap.isEmpty
    }

    //复制
    override def copy(): AccumulatorV2[String, mutable.Map[String, Int]] = {
      new wcAcc()
    }

    //清空
    override def reset(): Unit = wcMap.clear()

    //todo 分区内的累加
    override def add(key: String): Unit = {
      // TODO:  map的累加
      val oldValue: Int = wcMap.getOrElse(key, 0)
      // TODO: 更新  进行累加w
      wcMap.update(key,oldValue+1)
    }

    //todo 分区间合并
    override def merge(other: AccumulatorV2[String, mutable.Map[String, Int]]): Unit = {
      // TODO:  2个map进行累加
      val map1: mutable.Map[String, Int] = wcMap
      val map2: mutable.Map[String, Int] = other.value
      map2.foreach {
        case (word, count) => {
          val oldValue: Int = map1.getOrElse(word, 0)
          map1.update(word,count+oldValue)
        }
      }
    }


    // TODO: 返回结果 map
    override def value: mutable.Map[String, Int] = wcMap
  }
}
