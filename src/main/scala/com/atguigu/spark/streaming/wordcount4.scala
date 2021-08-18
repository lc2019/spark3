package com.atguigu.spark.streaming

import org.apache.spark.SparkConf
import org.apache.spark.streaming.dstream.{DStream, ReceiverInputDStream}
import org.apache.spark.streaming.{Seconds, StreamingContext, StreamingContextState}

/**
 * @author lzc
 * @date 2021-07-05 23:19
 */
object wordcount4 {
  def main(args: Array[String]): Unit = {
    val conf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("straming")
    //准备环境
    val ssc = new StreamingContext(conf, Seconds(3))
    ssc.checkpoint("cp")

    val ds: ReceiverInputDStream[String] = ssc.socketTextStream("localhost", 9999)


    ds.print()
    ssc.start()
    // TODO: 启动采集
    // TODO: 启动采集
    new Thread(new Runnable {
        override def run(): Unit = {
          while (true) {
            Thread.sleep(10000)
            val state: StreamingContextState = ssc.getState()
            if (state == StreamingContextState.ACTIVE) {
              ssc.stop(true, true)
              System.exit(0)
            }
          }
        }
      }
    ).start()
    ssc.awaitTermination()
  }
}
