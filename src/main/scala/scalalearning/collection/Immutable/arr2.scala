package scalalearning.collection.Immutable

/**
 * @author lzc
 * @date 2021-07-10 11:08
 *       todo 集合的定义访问 遍历
 */
object arr2 {
  def main(args: Array[String]): Unit = {
    val arr1: Array[Int] = Array(1, 2, 3, 4)
    val arr2: Array[Int] = Array(5, 6, 7, 8)
    // TODO: array为不可变数组 产生新的数组
    //    val ints: Array[Int] = arr1.+:(5)
    val ints: Array[Int] = arr1 :+ 5
    println(ints.mkString(","))
    println(arr1.mkString(","))

    val ints1: Array[Int] = arr1 ++: arr2
    ints1.foreach(println)


    def test(i: Int): Unit = {
      println(i)
    }

    ints1.foreach(test)
    //匿名函数 函数名省略
    ints1.foreach((i: Int) => {
      println(i)
    })
    //一行{}省略
    ints1.foreach((i: Int) => println(i))
    //类型推断 参数类型省略
    ints1.foreach((i) => println(i))
    //参数只有1搁出现1次
    ints1.foreach(println(_))
    //本身实现功能
    ints1.foreach(println)

  }
}
