package scalalearning.collection.mutable

/**
 * @author lzc
 * @date 2021-07-10 11:08
 *       todo 集合的定义访问 遍历
 */
object seqmethod {
  def main(args: Array[String]): Unit = {
    //todo seq 不可变序列
    val a: List[Int] = List(1, 2, 3, 4)
    val b: List[Int] = List(5, 6, 7, 8)

    //链接
    val list: List[Int] = List.concat(a, b)
    println(list)
  }
}
