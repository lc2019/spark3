package scalalearning.base

import scala.util.control.Breaks._


/**
 * @author lzc
 * @date 2021-07-08 9:07
 */
object loopDemo {
  def main(args: Array[String]): Unit = {

    for (char <- "abababab") {
      println(char, char.getClass)
    }

    for (i <- 1.to(10)) {
      println(i, i.getClass)
    }
    //Range(self, end, step)
    for (i <- 1 until(5, 2) reverse) {
      println(i, i.getClass)
    }

    //循环守卫
    for (i <- 1 to 10 if i % 2 == 0) {
      println(i)
    }

    for (elem <- 1 to 10 if elem > 5) {
      println(elem)
    }
    var isstop = false
    for (elem <- 1 to 10 if !isstop) {
      if (elem > 8) isstop = true
      println(elem)
    }
    //中断
    breakable {
      for (elem <- 1 to 10) {
        if (elem == 5) break
        println(elem)
      }
    }
    //如果使用return必须强制生声明类型
    for (elem <- 1 to 10) {
      if (elem == 5) return elem
      println(elem)
    }
  }
}
