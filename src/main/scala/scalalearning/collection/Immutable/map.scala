package scalalearning.collection.Immutable

/**
 * @author lzc
 * @date 2021-07-10 15:28
 */
object map {
  def main(args: Array[String]): Unit = {
    // TODO: key 不能重复且无序 如果key相同则会进行覆盖
    val map: Map[String, Int] = Map("a" -> 1, "b" -> 2, "c" -> 3)
    println(map)

    val map1: Map[String, Int] = map + ("d" -> 4)
    println(map1)

    println(map eq map1)

    println(map ++ map1)
  }
}
