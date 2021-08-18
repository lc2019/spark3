package scalalearning.func

/**
 * @author lzc
 * @date 2021-07-08 9:38
 *       函数式编程 功能的封装
 */
object funcDemo6 {
  def main(args: Array[String]): Unit = {
    /**
     * 1.函数最后一行作为返回值，return省
     * 2.根据最后一行可以推断函数类型，返回值类型可以省略
     * 3.只有一行{}省略
     * 4.没有返回值=省略
     * 5.没有参数（）省略
     * 6.没有参数列表没有省略（）调用可以不加（）
     */
    // TODO: 匿名函数  省略def关键字和 函数名
    // todo 1.匿名函数赋值给变量使用
    val fun = () => {
      println("匿名函数的执行")
    }
    //把函数赋值给变量 如果只想将函数对象给f 可以使用_
    val f: () => Unit = fun
    //    val f2 = fun _

    //todo 函数类型 () => Unit
    f()

    def fun2(name: String): Unit = {
      println("name: " + name)
    }

    // TODO: 有参数的函数类型
    //    val f1: (String) => Unit = fun2
    //    f1("lc")


    // TODO:  函数作为参数传递
    def fun3(f: () => Unit): Unit = {
      f()
    }

    fun3(fun)

    // TODO: 函数作为参数传递 有参数
    def fun4(f: (String) => Unit): Unit = {
      f("lc & ll")
    }

    fun4(fun2)

    def test(): String = {
      "zhangsan"
    }

    def fun5(): () => String = {
      //返回函数体本身
       test _
    }
    //返回函数体本身
    val f2: () => () => String =  fun5 _
    //第一个（）是调用fun5 第二个（）事调用test
    println(f2()())
  }
}
