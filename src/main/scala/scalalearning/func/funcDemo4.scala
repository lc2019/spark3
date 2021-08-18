package scalalearning.func

/**
 * @author lzc
 * @date 2021-07-08 9:38
 */
object funcDemo4 {
  def main(args: Array[String]): Unit = {
    def f(): Unit ={
      println("hello scala")
    }
    def f1()={
      //_ 不会执行 返回函数本身 而不是返回函数调用的结果
      f _
    }
    def f2() = {
      //返回函数调用
      f()
    }

    f1()()
    f2()
  }
}
