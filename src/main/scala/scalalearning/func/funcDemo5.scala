package scalalearning.func

/**
 * @author lzc
 * @date 2021-07-08 9:38
 *       函数式编程 功能的封装
 */
object funcDemo5 {
  def main(args: Array[String]): Unit = {
    // TODO: 函数只有一行{}可以省略 
    def fun1(name: String) = name

    // TODO: 函数参数没有()可以省略
    def fun2 = "lc"

    /**
     * 1.函数最后一行作为返回值，return省
     * 2.根据最后一行可以推断函数类型，返回值类型可以省略
     * 3.只有一行{}省略
     * 4.没有返回值=省略
     * 5.没有参数（）省略
     * 6.没有参数列表没有省略（）调用可以不加（）
     */

    def test(): Unit = {
      println("hello scala")
    }
    // TODO: 匿名函数  省略def关键字和 函数名
    // 匿名函数的调用使用赋值给变量
    val fun = () => {
      println("匿名函数的执行")
    }
    fun()
  }


}
