package com.atguigu.spark.rdds

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

import java.io.Serializable

/**
 * @author lzc
 * @date 2021-07-11 21:22
 */
object serial {
  def main(args: Array[String]): Unit = {
    // TODO: 序列化
    val sc = new SparkContext(new SparkConf().setMaster("local[*]").setAppName("rdd"))
    val rdd: RDD[Int] = sc.makeRDD(List(1, 2, 3, 4))
    //todo 在driver端执行
    //    val user: User = new User
    //方法外部的代码都在driver端
    //方法内部的代码都是在executor端
    //    val srdd: Unit = rdd.foreach(
    //      num => {
    //        println(num + user.age)
    //      }
    //    )

    rdd.foreach(
      num => {
        // TODO: 在executor端执行
        val user: User = new User()
        user.age = 10
        println(num + user.age)
      }
    )

  }

  class User {
    var age: Int = _
  }

  // TODO: 需要使用序列化 传递到不同的executor里面执行
  //  class User extends Serializable {
  //    var age: Int = _
  //  }
}
