package scalalearning.func

/**
 * @author lzc
 * @date 2021-07-10 9:38
 *       函数式编程 功能的封装
 */
object funcDemo9 {
  def main(args: Array[String]): Unit = {
    //todo 递归必须明确指定返回值类型
    def factor(num: Int): Int = {
      if (num <= 1) {
        1
      } else {
        num * factor(num - 1)
      }
    }

    def factor2(num: Int, res: Int): Int = {
      if (num <= 1) {
        res
      } else {
        factor2(num - 1, res * num)
      }
    }

    println(factor(5))
    println(factor2(5, 1))
  }
}
