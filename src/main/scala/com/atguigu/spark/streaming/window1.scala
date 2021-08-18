package com.atguigu.spark.streaming

import org.apache.spark.SparkConf
import org.apache.spark.streaming.dstream.{DStream, ReceiverInputDStream}
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
 * @author lzc
 * @date 2021-07-05 23:19
 */
object window1 {
  def main(args: Array[String]): Unit = {
    val conf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("straming")
    //准备环境
    val ssc = new StreamingContext(conf, Seconds(3))
    ssc.checkpoint("cp")

    val ds: ReceiverInputDStream[String] = ssc.socketTextStream("localhost", 9999)

    val fds: DStream[String] = ds.flatMap(line => {
      line.split(" ")
    })

    val mds: DStream[(String, Int)] = fds.map(
      (word: String) => (word, 1)
    )

    // TODO: 窗口范围 采集周期的整数倍  窗口划动的步长
    val wds: DStream[(String, Int)] = mds.window(Seconds(6), Seconds(3))

    val rds: DStream[(String, Int)] = wds.reduceByKey(_ + _)
    rds.print()

    // TODO: 启动采集
    ssc.start()
    ssc.awaitTermination()
  }
}
