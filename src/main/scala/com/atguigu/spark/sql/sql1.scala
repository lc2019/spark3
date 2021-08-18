package com.atguigu.spark.sql

import org.apache.spark.SparkConf
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{DataFrame, Dataset, Row, SparkSession}

/**
 * @author lzc
 * @date 2021-07-12 21:38
 * spark sql api
 */
object sql1 {
  def main(args: Array[String]): Unit = {
    // TODO: 构建spark sql环境
    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("sql")
    val ss: SparkSession = new SparkSession.Builder().config(sparkConf).getOrCreate()


    val df: DataFrame = ss.read.json("datas/user.json")
    // TODO: show
    df.show()

    // TODO:视图 sql语法
    df.createOrReplaceTempView("user")
    ss.sql("select * from user").show

    // TODO: dsl语法
    df.select("username","age").show()
    // TODO: 使用dsl语法必须使用隐式转换   rdd ds df 相互操作
    import ss.implicits._
    df.select($"username",$"age"+1).show()

    val rdd: RDD[(Int, String, Int)] = ss.sparkContext.makeRDD(List(
      (1, "lc", 30),
      (2, "ll", 28)
    )
    )
    // TODO:  toDF
    val df1: DataFrame = rdd.toDF("id", "name", "age")
    df1.select("id","name","age").show()


    // TODO:   df->ds 样例类
    val ds: Dataset[User] = df1.as[User]
    ds.show()

    // TODO: ds-df
    val df2: DataFrame = ds.toDF()
    df2.show()

    // TODO:  df ->rdd
    val urdd: RDD[Row] = df2.rdd

    // TODO: rddd->类型 ->ds
    val mrdd: RDD[User] = rdd.map {
      case (id, name, age) => User(id, name, age)
    }
    val ds1: Dataset[User] = mrdd.toDS()

    ds1.show()

    ss.stop()

  }
  case  class User(id:Int,name:String,age:Int)
}
