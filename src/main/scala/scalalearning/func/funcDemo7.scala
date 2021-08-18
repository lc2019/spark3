package scalalearning.func

/**
 * @author lzc
 * @date 2021-07-08 9:38
 *       函数式编程 功能的封装
 */
object funcDemo7 {
  def main(args: Array[String]): Unit = {
    /**
     * 1.函数最后一行作为返回值，return省
     * 2.根据最后一行可以推断函数类型，返回值类型可以省略
     * 3.只有一行{}省略
     * 4.没有返回值=省略
     * 5.没有参数（）省略
     * 6.没有参数列表没有省略（）调用可以不加（）
     */

    // TODO: 函数的高级用法
    def op(x: Int, y: Int, f: (Int, Int) => Int): Int = {
      f(x, y)
    }

    def js(x: Int, y: Int): Int = {
      x + y
    }

    println(op(10, 20, js))
    // TODO: 匿名函数
    println(op(10, 20, (x: Int, y: Int) => {
      x * y
    }))
    //todo 1.只有1行{} 省略
    println(op(10, 20, (x: Int, y: Int) => x * y))
    //todo 2.类型可以推断出来 类型可以省略
    println(op(10, 20, (x, y) => x * y))
    //todo 3.参数只使用了1次可以使用 并且按照顺序使用 可以使用_代替 参数列表省略
    println(op(10, 20, _ - _))


    def fun(f:(String)=>Unit): Unit ={
       f("lc")
    }
    def ptn(name: String): Unit = {
      println(name)
    }

    //匿名函数// TODO:  只有1行{}省略
    fun((name: String)=>{println(name)})
    // TODO: 类型可以推断出来参数类型省略
    fun((name: String)=>println(name))
    fun((name)=>println(name))
    // TODO: 匿名函数参数只有1搁参数（）省略
    fun(name=>println(name))
    fun(println(_))


  }
}
