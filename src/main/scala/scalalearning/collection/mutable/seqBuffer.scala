package scalalearning.collection.mutable

import scala.collection.mutable.ListBuffer

/**
 * @author lzc
 * @date 2021-07-10 11:08
 *       todo 集合的定义访问 遍历
 */
object seqBuffer {
  def main(args: Array[String]): Unit = {
    //todo buffer 可变序列
    val buffer: ListBuffer[String] = ListBuffer[String]()
    buffer.append("a", "b", "c")
    println(buffer)
    buffer.update(0, "d")
    println(buffer)
    buffer.insert(3, "a")
    println(buffer)
    buffer.remove(2, 2)
    println(buffer)

  }
}
