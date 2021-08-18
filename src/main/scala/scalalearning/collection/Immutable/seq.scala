package scalalearning.collection.Immutable

/**
 * @author lzc
 * @date 2021-07-10 11:08
 *       todo 集合的定义访问 遍历
 */
object seq {
  def main(args: Array[String]): Unit = {
    //todo seq 不可变序列
    val ints: List[Int] = List(1, 2, 3, 4)
    val ints1: List[Int] = ints :+ 5

    println(ints1)
    val ints2: List[Int] = 6 :: ints1
    println(ints2)

    //添加新元素到集合
    val list: List[Int] = 1 :: 2 :: 3 :: 4 :: List()
    println(list)
    // TODO: 需要添加集合 传递集合的元素到intes2 ：：： 
    val list2: List[Int] = 1 :: 2 :: list ::: ints2
    println(list2)
    //todo 将集合作为整体直接添加进Nil List(1, 2, List(1, 2, 3, 4))
    val list3 = 1 :: 2 :: list :: Nil
    println(list3)
  }
}
