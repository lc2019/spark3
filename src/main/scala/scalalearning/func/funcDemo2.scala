package scalalearning.func

/**
 * @author lzc
 * @date 2021-07-08 9:38
 */
object funcDemo2 {
  def main(args: Array[String]): Unit = {
    val  func = (x:Int,y:Int)=>x+y
    println(m1(2,3,func))

    println(func2(func, 10, 20))
  }

  def m1(a:Int,b:Int,func:(Int,Int)=>Int): Int ={
     func(a,b)
  }

  //函数定义 func:(Int,Int)=>Int
  val func2 =(func:(Int,Int)=>Int,a:Int,b:Int)=>func(a,b)
}
