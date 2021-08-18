package com.atguigu.spark.streaming

import org.apache.kafka.clients.consumer.{ConsumerConfig, ConsumerRecord}
import org.apache.spark.SparkConf
import org.apache.spark.streaming.dstream.{DStream, InputDStream}
import org.apache.spark.streaming.kafka010.{ConsumerStrategies, KafkaUtils, LocationStrategies}
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
 * @author lzc
 * @date 2021-07-13 17:45
 */
object demo1 {
  def main(args: Array[String]): Unit = {
    //1.创建 SparkConf
    val sparkConf: SparkConf = new
        SparkConf().setAppName("ReceiverWordCount").setMaster("local[*]")

    //2.创建 StreamingContext
    val ssc = new StreamingContext(sparkConf, Seconds(3))

    //3.定义 Kafka参数
    val kafkaPara: Map[String, Object] = Map[String, Object](
      ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG ->
        "Hadoop102:9092,Hadoop103:9092,Hadoop104:9092",
      ConsumerConfig.GROUP_ID_CONFIG -> "atguigu",
      "key.deserializer" ->
        "org.apache.kafka.common.serialization.StringDeserializer",
      "value.deserializer" ->
        "org.apache.kafka.common.serialization.StringDeserializer"
    )

    //4.读取 Kafka数据创建 DStream
    val kafkaDStream: InputDStream[ConsumerRecord[String, String]] =
      KafkaUtils.createDirectStream[String, String](ssc,
        LocationStrategies.PreferConsistent,
        ConsumerStrategies.Subscribe[String, String](Set("atguigu"), kafkaPara))

    //5.将每条消息的 KV取出
    val valueDStream: DStream[String] = kafkaDStream.map(record => record.value())

    //6.计算 WordCount
    valueDStream.flatMap(_.split(" "))
      .map((_, 1))
      .reduceByKey(_ + _)
      .print()

    //7.开启任务
    ssc.start()
    ssc.awaitTermination()
  }
}
