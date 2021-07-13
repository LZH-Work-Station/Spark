package chapiter08_函数和方法

object TestEasyFunction2 {
  def main(args: Array[String]): Unit = {

    //10. 把函数作为参数
    val plus = (a: Int, b: Int) => a + b
    val minus = (a: Int, b: Int) => a - b
    // 外面的函数相当于参数里的函数的抽象 详情看03
    def calcule(f: (Int, Int) => Int, a: Int, b: Int) = {
      println(f(a, b))
    }

    calcule(plus, 4, 5)
    calcule(minus, 5, 4)
    //11. 把函数作为返回值
    def calcul2(s: String): (Int, Int) => Unit = {
      def add(a: Int, b: Int): Unit = {
        println(a + b)
      }
      // 不能用if啊 while啊之类的包裹返回的 function
      add


    }

    println(calcul2("add")(4, 5))

  }
}
