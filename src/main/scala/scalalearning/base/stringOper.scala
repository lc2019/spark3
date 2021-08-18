package scalalearning.base

/**
 * @author lzc
 * @date 2021-07-08 8:50
 */
object stringOper {
  def main(args: Array[String]): Unit = {
    val  name = "lc"
    val age = "20".toInt
    println(s"name=$name,age=${age+10}")
  }
}
