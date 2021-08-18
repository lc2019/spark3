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
object acc2 {
  def main(args: Array[String]): Unit = {
    val sc = new SparkContext(new SparkConf().setMaster("local[*]").setAppName("rdd"))
    val rdd: RDD[Int] = sc.makeRDD(List(1, 2, 3, 4, 5, 6), 2)

    //    var sum = 0
    // TODO: 累加器
    val sum: LongAccumulator = sc.longAccumulator("sum")
    rdd.foreach(
      //executor执行逻辑
      num => {
        //        sum = num + sum
        // TODO: 使用累加器
        sum.add(num)
      }

    )
    // TODO:  driver执行 没有返回sum的结果 sum不变  通过累加器解决
    println(sum.value)
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
      wcMap.put(key,oldValue+1)
    }

    //todo 分区间合并
    override def merge(other: AccumulatorV2[String, mutable.Map[String, Int]]): Unit = {
      // TODO:  2个map进行累加
      val map1: mutable.Map[String, Int] = this.wcMap
      val map2: mutable.Map[String, Int] = other.value
      map2.foreach {
        case (word, count) => {
          val oldValue: Int = map2.getOrElse(word, 0)
          map1.put(word,count+oldValue)
        }
      }
    }


    // TODO: 返回结果 map
    override def value: mutable.Map[String, Int] = wcMap
  }
}
