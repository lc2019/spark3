package scalalearning.collection.mutable

import scala.collection.mutable
import scala.collection.mutable.ListBuffer

/**
 * @author lzc
 * @date 2021-07-10 11:08
 *       todo 集合的定义访问 遍历
 */
object seqBuffer2 {
  def main(args: Array[String]): Unit = {
    //todo buffer 可变序列
    val buffer: ListBuffer[String] = ListBuffer("a", "b", "c")
    val buffer1: buffer.type = buffer += "d"
    println(buffer eq buffer1)

    val list: List[String] = buffer.toList
    val buffer2: mutable.Buffer[String] = list.toBuffer
    println(list)
    println(buffer2)
  }
}
