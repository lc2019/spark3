package scalalearning.func

/**
 * @author lzc
 * @date 2021-07-10 9:38
 *       函数式编程 功能的封装
 */
object funcDemo8 {
  def main(args: Array[String]): Unit = {
    /**
     * 1.函数最后一行作为返回值，return省
     * 2.根据最后一行可以推断函数类型，返回值类型可以省略
     * 3.只有一行{}省略
     * 4.没有返回值=省略
     * 5.没有参数（）省略
     * 6.没有参数列表没有省略（）调用可以不加（）
     */
    // TODO: 闭包效果  改变外部变量的声明周期
    // TODO: 将外部变量复制一份给inner函数保证变量有效  避免被test回收
    def test(i: Int) = {
      def iner(j: Int) = {
        //直接传递一个函数
        def op(f: (Int, Int) => Int) = {
        }
        //返回一个函数
        op _
      }
      //返回一个函数
      iner _
    }

    //    val f= test(10)
    //    f(20)
    // TODO: 直接封装函数的逻辑
    test(10)(20)(_ - _)
  }
}
