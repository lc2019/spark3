package scalalearning.mxdx

import scala.Predef._


/**
 * @author lzc
 * @date 2021-07-08 10:32
 */
object classDemo2 {
  def main(args: Array[String]): Unit = {
    val user = new User3 with MyTrait2
   user.run()
  }

  trait MyTrait2 {
     def run(): Unit ={
       println("run...")
     }
  }
//相同特征
  trait MyTrait {
    def run(): Unit

    def test(name: String): String = {
      "name= " + name
    }
  }
  //实现特质  混入特质
  class persion extends MyTrait {
    override def run(): Unit = {

      println("run....")
    }
  }
}
