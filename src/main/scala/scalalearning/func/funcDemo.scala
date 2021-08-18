package scalalearning.func

/**
 * @author lzc
 * @date 2021-07-08 9:38
 * 函数式编程 功能的封装
 */
object funcDemo {
  def main(args: Array[String]): Unit = {
    //调用 没有参数（）可以省略 add
    add()
    println(add(1, 2))
    println(add(1, 2, 3, 4, 5))

    val seq: Range.Inclusive = 1 to 10
    //相当于把seq展开 然后一个个参数传递进行add运算  seq:_*
    println(add(seq: _*))

    println(add())
    println(add(1, 2))
    println(add(1))
    //WrappedArray(lc, ll)
    test("lc", "ll")
  }

  // TODO: 1.无参数 无返回值
  def add(): Unit = {
    println("add")
  }

  // TODO: 2.无参有返回值
  def add1(): Int = {
    println("add")
    //最后一行就事返回值
    1
  }
  def add2(a: Int, b: Int): Int = {
    a + b
  }

  //可变参数 todo 多参数 有返回值
  def add(a: Int*): Int = {
    a.sum
  }

  //函数的默认值  todo 有参数有返回值
  def add(a: Int = 10, b: Int = 20): Int = {
    a + b
  }

  //可变参数 todo 有参数无返回值
  def test(name: String*): Unit = {
    println(name)
  }
}
