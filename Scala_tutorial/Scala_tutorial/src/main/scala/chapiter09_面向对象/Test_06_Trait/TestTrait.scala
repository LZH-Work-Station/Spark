package chapiter09_面向对象.Test_06_Trait

object TestTrait {
  def main(args: Array[String]): Unit = {
    val myBall : Ball1 = new BasketBall
    myBall.describe
  }
}
trait Ball1{
  var count: Int
  def describe: Unit
}

class BasketBall extends Ball1{
//  1）基有父类：class 类名 extends 父类 with 特质1 with 特质2 with 特质3…
//
//  2）说明
//
//   （1）类和特质的关系：使用继承的关系。
//
//   （2）当一个类去继承特质时，第一个连接词是extends，后面是with。
//
//   （3）如果一个类在同时继承特质和父类时，应当把父类写在extends后。
  override var count: Int = 1

  override def describe: Unit = {
    println("I have " + count + " ball")
  }
}


