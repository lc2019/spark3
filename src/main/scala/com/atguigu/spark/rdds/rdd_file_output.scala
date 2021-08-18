package com.atguigu.spark.rdds

import org.apache.spark.{SparkConf, SparkContext}

/**
 * @author lzc
 * @date 2021-08-17 21:20
 */
object rdd_file_output {
  def main(args: Array[String]): Unit = {
    val sc: SparkContext = new SparkContext(new SparkConf().setMaster("local[*]").setAppName("file"))
    // TODO: rdd创建 从文件读取数据 


    // TODO: 默认的分区
    // math.min(defaultParallelism, 2)
    // TODO: spark 读取文件分区操作是hadoop确定
    // totalSize += file.getLen(); 总的字节数 7
    //numSplits 2
    //goalSize = totalSize / numSplits>=1    ==》3
    //1.1倍  如果剩余大于1.1就在分一个区
    sc.textFile("datas/3.txt",2).saveAsTextFile("output1");
//    sc.textFile("datas/4.txt",3).saveAsTextFile("output");
    // TODO: 分析 hadoop 读取偏移量 按行读取 不会重复读取数据
    // 1.totalSize =  14 byte   numSplits =3  goalSize = 14/3 = 4 余2个byte 所以2/4》0.1 5个分区
    /** 2.bytesRemaining /splitSize > SPLIT_SLOP 1.1 )  14/4 = 3 余 2
     * 3.//splitSize = computeSplitSize(goalSize, minSize, blockSize);
     * 123@@   01234        1# ---》123
       @@      4-8          2#----45 按行读取偏移量
       45@@    8-12        3#  空
       @@      13 14         4# 6
       6
     */

    sc.stop();
  }
}
