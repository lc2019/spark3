package scalalearning.collection

/**
 * @author lzc
 * @date 2021-07-10 17:53
 */
object method {
  def main(args: Array[String]): Unit = {
    //todo 集合的方法
    val list: List[Int] = List(1, 2, 3, 4, 5, 6, 7, 8)

    //todo 集合的长度
    println(list.size)
    println(list.length)

    //todo 遍历
    println(list.iterator)
    println(list.mkString(","))
    list.foreach(println)

    // TODO: 集合元素判断
    println(list.contains(2))

    // TODO: 数据的获取  take 数据的前几
    println(list.take(2)) //前几位·
    println(list.takeRight(2)) //后几位

    //todo 映射 需要传递一个函数 自定义实现
    val list1: List[Int] = list.map(_ * 2)
    println(list1)
  }
}
