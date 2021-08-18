package scalalearning.collection.mutable

import scala.collection.mutable

/**
 * @author lzc
 * @date 2021-07-10 15:16
 */
object setBuffer {
  def main(args: Array[String]): Unit = {
    // TODO: 无序的不重复的集合 
    val set1 = mutable.Set(1, 2, 3, 4)
    set1.add(5)
    println(set1)
  }
}
