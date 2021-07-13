package chapiter05_数据类型

object TestString {
  def main(args: Array[String]): Unit = {
    // == 直接调用equals方法，不是比较地址，而是比较值
    var s1 : String = "hello"
    var s2 : String = "hello"
    s2 = s2.reverse
    println(s2)
    println(s1==s2)
  }
}
