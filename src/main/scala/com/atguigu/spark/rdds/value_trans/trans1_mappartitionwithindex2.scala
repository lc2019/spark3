package com.atguigu.spark.rdds.value_trans

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * @author lzc
 * @date 2021-07-07 21:40
 */
object trans1_mappartitionwithindex2 {
  def main(args: Array[String]): Unit = {
    //准备环境
    val sc = new SparkContext(new SparkConf().setMaster("local[*]").setAppName("rdd"))
    //以分区为单位进行数据转行，整个分区的数据加载到内存，处理完的数据也不会被释放
    val rdd: RDD[Int] = sc.makeRDD(List(1, 2, 3, 4))

    //求每个分区的最大值
    val mrdd: RDD[(Int, Int)] = rdd.mapPartitionsWithIndex(
      //      f: (Int, Iterator[T]) => Iterator[U]
      (index, iter) => {
        iter.map {
          iter =>
            //获取数据在那个分区--tuple
            (index, iter)
        }
      }
    )
    mrdd.collect().foreach(println)

    sc.stop()
  }
}
