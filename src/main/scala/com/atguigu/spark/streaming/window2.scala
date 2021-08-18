package com.atguigu.spark.streaming

import org.apache.spark.SparkConf
import org.apache.spark.streaming.dstream.{DStream, ReceiverInputDStream}
import org.apache.spark.streaming.{Seconds, StreamingContext, StreamingContextState}

/**
 * @author lzc
 * @date 2021-07-05 23:19
 */
object window2 {
  def main(args: Array[String]): Unit = {
    val conf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("straming")
    //准备环境
    val ssc = new StreamingContext(conf, Seconds(3))
    ssc.checkpoint("cp2")

    val ds: ReceiverInputDStream[String] = ssc.socketTextStream("localhost", 9999)

    val fds: DStream[String] = ds.flatMap(line => {
      line.split(" ")
    })

    val mds: DStream[(String, Int)] = fds.map(
      (word: String) => (word, 1)
    )

    val rds: DStream[(String, Int)] = mds.reduceByKeyAndWindow(
      (x: Int, y: Int) => x + y,
      (x: Int, y: Int) => x - y,
      Seconds(6),
      Seconds(3),
    )

    rds.print()

    ssc.start()


    ssc.awaitTermination()
  }
}
