package scalalearning.collection.mutable

import scala.collection.mutable

/**
 * @author lzc
 * @date 2021-07-10 15:28
 */
object mapmutable {
  def main(args: Array[String]): Unit = {
    // TODO: key 不能重复且无序 如果key相同则会进行覆盖
    val map: mutable.Map[String, Int] = mutable Map("a" -> 1, "b" -> 2, "c" -> 3)

    map.put("d",4)
    println(map)
    // TODO: 创建新的map
    val map1: mutable.Map[String, Int] = map + ("e" -> 5)
    //返回原来的集合
    val map2: mutable.Map[String, Int] = map += ("e" -> 5)
    println(map eq map1) //false
    println(map eq map2) //true

    map.update("a",10)
    println(map)

    map.remove("e")
    println(map)

//    println(map.clear())

    //todo get 获取结果返回类型 Some None 如果存在返回some 不存在返回None
    println(map.get("b").get)
    //todo 没有值给默认值 避免空指针异常
    println(map.getOrElse("e", -1))

    //获取所有的key v
    val keys: Iterable[String] = map.keys
    //迭代器·
    val iterator: Iterator[String] = keys.iterator
    while (iterator.hasNext){
      println(iterator.next())
    }
    //直接获取迭代器
    val values: Iterator[Int] = map.valuesIterator
    values.foreach(println)
  }
}
