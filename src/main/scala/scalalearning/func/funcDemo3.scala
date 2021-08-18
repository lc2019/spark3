package scalalearning.func

/**
 * @author lzc
 * @date 2021-07-08 9:38
 */
object funcDemo3 {
  def main(args: Array[String]): Unit = {
    println(add(1, 2)(3, 4))
  }
  //柯里化
  def add(x:Int,y:Int)(m:Int,n:Int) = x+y+m+n
}
