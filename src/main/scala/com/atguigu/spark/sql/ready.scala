package com.atguigu.spark.sql

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession

/**
 * @author lzc
 * @date 2021-07-13 9:52
 */
object ready {
  def main(args: Array[String]): Unit = {
    // TODO: 提示权限问题
    System.setProperty("HADOOP_USER_NAME", "atguigu")
    val ss: SparkSession = new SparkSession.Builder().enableHiveSupport().
      config(new SparkConf().setMaster("local[*]").setAppName("sql")).getOrCreate()

    ss.sql("use sparksql")
    // TODO: 建表
    ss.sql(
      """
        |CREATE TABLE `user_visit_action`(
        |`date` string,
        |`user_id` bigint,
        |`session_id` string,
        |`page_id` bigint,
        |`action_time` string,
        |`search_keyword` string,
        |`click_category_id` bigint,
        |`click_product_id` bigint,
        |`order_category_ids` string,
        |`order_product_ids` string,
        |`pay_category_ids` string,
        |`pay_product_ids` string,
        |`city_id` bigint)
        |row format delimited fields terminated by '\t'
        |""".stripMargin
    )

    ss.sql(
      """
        |CREATE TABLE `product_info`(
        |`product_id` bigint,
        |`product_name` string,
        |`extend_info` string)
        |row format delimited fields terminated by '\t'
        |""".stripMargin)

    ss.sql(
      """
        |CREATE TABLE `city_info`(
        |`city_id` bigint,
        |`city_name` string,
        |`area` string)
        |row format delimited fields terminated by '\t'
        |""".stripMargin)
    // TODO: 导入数据
    ss.sql("load data local inpath 'input/user_visit_action.txt' into table sparksql.user_visit_action")
     ss.sql(
       """
         |    load data local inpath 'input/product_info.txt' into table sparksql.product_info
         |""".stripMargin)
    ss.sql(
      """
        |    load data local inpath 'input/city_info.txt' into table sparksql.city_info
        |""".stripMargin)

    ss.sql("select * from city_info").show(20,false)

  }
}
