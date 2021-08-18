package com.atguigu.spark.rdds

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * @author lzc
 * @date 2021-07-07 16:17
 * spark读取文件时候的分区计算
 */
object rddCreate_partitioner {
  def main(args: Array[String]): Unit = {
      //准备环境
      val sc  = new SparkContext(new SparkConf().setMaster("local[*]").setAppName("rdd"))

    /**
     * 14byte/ 2个分区 = 每次读取7byte
     * 1234567@@  ---》 偏移量012345678   【0号分区】 [0-7]----1234567
     * 89@@       ----》 偏移量9101112    【1号分区】[7-14] 890
     * 0          ----》 偏移量13
     *
     * totalsize = 7  numslice=2 剩余1 占原来的33% 所以需要重新分区
     * 偏移量是按行来读取
     * 1@@   0号分区偏移量 012 【0-3】
     * 2@@   1号分区偏移量 345 【3-6】
     * 3     2号分区偏移量6     【7】
     * 所以分区文件是0# 12 1#3 2# 空
     * spark 读取文件根据hadoop来决定
     * totalsize 总的文件字节数
     * numslice 2
     * 剩余的跟之前的比较是否>10% 如果大于10%重新分区
     */
    val frdd: RDD[(String)] = sc.textFile("datas/3.txt", 2)

    //保存
    frdd.saveAsTextFile("out")
    //关闭环境
    sc.stop()
  }
}
