package scalalearning.mxdx

import scala.Predef._



/**
 * @author lzc
 * @date 2021-07-08 10:32
 */
object classDemo {
  def main(args: Array[String]): Unit = {
    val user = new User(20)
    println(user.name) //ll
    println(user.age) //10

    val user1 = new User("ll")
    println(user1.name2)
    user1.test()

    val user3 = User3.apply()
    val user4 = User3.apply()
    println(user3.eq(user4))

  }
}

object User3{

  private var user = new User3()
  def apply() ={
    user
  }
}
class  User3{

}
//主构造
class  User(val name:String,val age:Int)  {
  val name2: String = "lc"

  //辅助构造
  def this(name: String) {
    //name只能在当前辅助构造当作普通的常量使用，第一行必须是主构造
    this(name, 10)
  }

  def this(age: Int) {
    this("ll")
  }

  def test(): Unit = {
    println("test....")
  }
}