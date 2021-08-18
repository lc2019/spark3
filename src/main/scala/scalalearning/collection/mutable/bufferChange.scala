package scalalearning.collection.mutable

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

/**
 * @author lzc
 * @date 2021-07-10 14:32
 *       可变数组 伴生对象
 */
object bufferChange {
  def main(args: Array[String]): Unit = {
    val buffer: Array[Int] = Array[Int](1, 2, 3, 4)
    // TODO: 可变不可变集合的相互转换
    val buffer1: mutable.Buffer[Int] = buffer.toBuffer
    println(buffer1)

    val buffer2: ArrayBuffer[Int] = ArrayBuffer(1, 2, 3, 4)
    val array: Array[Int] = buffer2.toArray
    println(array.mkString(","))
  }
}
