package scalalearning.collection.Immutable

/**
 * @author lzc
 * @date 2021-07-10 17:13
 */
object tuple {
  def main(args: Array[String]): Unit = {
    //元素的集合 作为一个整体使用
    val tuple1: (Int, String, Int, Int) = (1, "lc", 2021, 30)
    println(tuple1._2)
    //遍历tuple
    val iterator: Iterator[Any] = tuple1.productIterator
    while (iterator.hasNext){
       println(iterator.next())
    }
    // TODO: 直接访问tuple的元素
    println(tuple1.productElement(3))

    val map: Map[String, Int] = Map("a" -> 1, "b" -> 2, "c" -> 3)
    // TODO: map的元素遍历就是一个个tuple 通过tuple可以直接获取 
    for (elem <- map) {
      println("key： "+elem._1 + " vale: "+elem._2)
    }
  }
}
