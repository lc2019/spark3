package scalalearning.collection.mutable


import scala.collection.mutable.ArrayBuffer

/**
 * @author lzc
 * @date 2021-07-10 14:32
 *       可变数组 伴生对象
 */
object arrayBuffer {
  def main(args: Array[String]): Unit = {
    //apply
    val buffer: ArrayBuffer[Int] = ArrayBuffer(1, 2, 3, 4)
    buffer.append(5)
    buffer += 5
    //构造
    val strings = new ArrayBuffer[String]()
    // TODO: 不会产生新的集合
    val res = strings.append("a", "b", "c")
    val res2 = strings.insert(3, "d")
    val res3 = strings.update(4, "e")
    println(strings)
  }
}
