package chapiter08_函数和方法

object TestLazy {
  def main(args: Array[String]): Unit = {
    // 当函数返回值被声明为lazy后，函数的执行将被推迟，直到首次使用这个函数， 但是不饿能修饰var类型的变量
    lazy val result: Int = sum(13, 47) // 本来再sum中的println并没有在这一行执行
    println("1. 函数调用")
    println("2. result = " + result)


  }
  def sum(a: Int, b: Int): Int = {
    println("a + b = ", a + b)
    a + b
  }

}
