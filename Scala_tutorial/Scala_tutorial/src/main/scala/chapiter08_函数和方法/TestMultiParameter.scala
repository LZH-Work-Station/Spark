package chapiter08_函数和方法

object TestMultiParameter {
  def main(args: Array[String]): Unit = {
    // (1) name会作为一个Array集合传入
    TestMultiParameter.printInfo("LI", "ZEHAN")
    // (2) 如果没有传入参数，name是一个空的List
    TestMultiParameter.printInfo()
    // (3) 参数默认值
    TestMultiParameter.printInfo1( "LI ZEHAN")
  }
  // 不定参数
  def printInfo(name : String*): Unit = {
    println(name)
  }

  def printInfo1( name : String, age : Int = 18): Unit = {
    println(name + age)
  }

}
