package chapiter08_函数和方法

object TestFuncAndMethod {
  def main(args: Array[String]): Unit = {
    //（1）为完成某一功能的程序语句的集合，称为函数。
    //（2）类中的函数称之方法。

    // 定义函数， 函数可以嵌套在方法下面
    def sayHello(name : String) : Unit = {
      println("hello" + name)
    }
    // 调用函数
    sayHello("LIZEHAN")
    // 调用方法
    TestFuncAndMethod.sayHello("JIAYIMENG")
  }

  // 定义方法， 方法是类下面的定义的函数
  def sayHello(name : String) : Unit = {
    println("hello" + name)
  }

}
