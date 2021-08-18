package com.atguigu.spark.sql

import org.apache.spark.SparkConf
import org.apache.spark.sql.{DataFrame, SparkSession}

/**
 * @author lzc
 * @date 2021-07-12 22:30
 */
object udf {
  def main(args: Array[String]): Unit = {
    val ss: SparkSession = new SparkSession.Builder().config(new SparkConf().setMaster("local[*]").setAppName("sql")).getOrCreate()

    val df: DataFrame = ss.read.json("datas/user.json")

    // TODO:  定义udf
    ss.udf.register("prefixName", (name: String) => "用户名： " + name)
    df.createOrReplaceTempView("user")

    ss.sql(" select prefixName(username) from user ").show

    ss.stop()
  }
}
