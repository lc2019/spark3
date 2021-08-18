package scalalearning.collection.Immutable

/**
 * @author lzc
 * @date 2021-07-10 11:08
 *       todo 集合的定义访问 遍历
 */
object arr {
  def main(args: Array[String]): Unit = {
    //todo 1.集合定义  Array【类型】【长度】
    val array = new Array[String](3)

    // TODO: 2.使用（）访问 初始化数组元素
    array(0) = "lc"
    array(1) = "ll"
    array(2) = "lc & ll"

    // TODO: 3.数据的访问
    println(array.mkString(","))
    println(array.toBuffer)

    //todo 4.循环遍历
    for (elem <- array) {
      println(elem)
    }

    // TODO: 5.数组的创建 apply可以省略
    val strings: Array[String] = Array.apply("1", "2", "3")
    //    val strings: Array[String] = Array("1", "2", "3")
    strings.foreach(println)

    strings.update(1, "10")
    println(strings.mkString(","))
  }
}
