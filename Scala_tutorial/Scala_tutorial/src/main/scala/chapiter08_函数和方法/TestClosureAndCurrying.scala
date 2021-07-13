package chapiter08_函数和方法

object TestClosureAndCurrying {
  def main(args: Array[String]): Unit = {
    // 闭包， 如果一个函数 访问到了他的外部变量（别的方法的局部变量），那么这个函数和他所处的环境叫闭包
    // 函数柯里化

    def addByFour(a: Int): Int=>Int = {
      // 定义局部变量可以传入内层的函数，等价于java中的内部类
      def add(b: Int): Int = {
        a + b
      }
      add
    }
    println(addByFour(1)(2))
  }
}
