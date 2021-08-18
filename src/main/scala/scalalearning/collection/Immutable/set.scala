package scalalearning.collection.Immutable

/**
 * @author lzc
 * @date 2021-07-10 15:16
 */
object set {
  def main(args: Array[String]): Unit = {
    // TODO: 无序的不重复的集合 
    val set1 = Set(1, 2, 3, 4)
    val set2 = Set(5,6,7,8)
    val set3: Set[Int] = set1 + 9
    println(set3)
    val set4: Set[Int] = set1 ++ set2
    println(set4)
  }
}
