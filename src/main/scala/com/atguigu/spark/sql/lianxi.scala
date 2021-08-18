package com.atguigu.spark.sql

import org.apache.spark.SparkConf
import org.apache.spark.sql.{Encoder, Encoders, SparkSession, functions}
import org.apache.spark.sql.expressions.Aggregator

import scala.collection.mutable
import scala.collection.mutable.ListBuffer

/**
 * @author lzc
 * @date 2021-07-13 9:52
 */
object lianxi {
  def main(args: Array[String]): Unit = {
    // TODO: 提示权限问题
    System.setProperty("HADOOP_USER_NAME", "atguigu")
    val ss: SparkSession = new SparkSession.Builder().enableHiveSupport().
      config(new SparkConf().setMaster("local[*]").setAppName("sql")).getOrCreate()

    ss.sql("use sparksql")

    ss.udf.register("remark", functions.udaf(new cityMark()))
    ss.sql(
      """
        |select *
        |from(
        |select *,rank() over(partition by area order by click desc) srank
        |from
        |(select area,product_name,count(*) click,
        |remark(city_name) cityremark
        |from
        |(select
        |a.*,
        |c.area,
        |p.product_name,
        |c.city_name
        |from user_visit_action a
        |join city_info c  on a.city_id = c.city_id
        |join product_info p on p.product_id = a.click_product_id
        |where a.click_product_id != -1
        |) t1 group by area,product_name)
        | t2)t3
        |where t3.srank<=3;
        |""".stripMargin).show(10, false)

  }

  // TODO:  总点击 （城市，点击）
  case class cityBuff(var total: Long, var citymap: mutable.Map[String, Long])

  /**
   * in 城市名称
   * buff
   * out 备注 输出字符串
   */
  class cityMark extends Aggregator[String, cityBuff, String] {
    override def zero: cityBuff = cityBuff(0L, mutable.Map[String, Long]())

    override def reduce(b: cityBuff, cityname: String): cityBuff = {
      // TODO: 总数+1 城市看是否存在+1
      b.total += 1L
      val value = b.citymap.getOrElse(cityname, 0L) + 1
      b.citymap.update(cityname, value)
      b
    }

    override def merge(b1: cityBuff, b2: cityBuff): cityBuff = {
      b1.total += b2.total
      b2.citymap.foreach {
        // TODO: 模式匹配 map
        case (cityname, cnt) => {
          val newcnt: Long = b1.citymap.getOrElse(cityname, 0L) + cnt
          b1.citymap.update(cityname, newcnt)
        }
      }
      b1
    }

    override def finish(buff: cityBuff): String = {
      val totalCnt: Long = buff.total
      val list = ListBuffer[String]()
      val citydata: List[(String, Long)] = buff.citymap.toList.sortBy(_._2)(Ordering.Long.reverse).take(2)

      val hasmore: Boolean = buff.citymap.size > 2
      var p = 100L
      citydata.foreach {
        case (city, cnt) => {

          val per: Long = cnt * 100 / totalCnt
          if (hasmore) {
            p = p - per
          }
          val s = city + " " + per + "%"
          list.append(s)
        }
      }
      if (hasmore) {
        list.append("其他 " + p + "%")
      }
      list.mkString(",")
    }

    override def bufferEncoder: Encoder[cityBuff] = Encoders.product

    override def outputEncoder: Encoder[String] = Encoders.STRING
  }
}
