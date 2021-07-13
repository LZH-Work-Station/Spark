package chapiter08_函数和方法

object TestControlAbstract {
  def main(args: Array[String]): Unit = {
    // 值传递
    def f0(a: Int): Int = {
      a + 4
    }

    def f1(a: Int): Int = {
      a + 9
    }

    val a = 5
    println(f1(f0(a)))

    // 传名参数，传的是代码块，Int是代码块的返回值
    def f2(a: => Int): Unit = {
      // a是一段代码块，作用是每使用一次a就调用一次这段代码块
      println("a: " + a)
      println("a: " + a)
    }

    val b = 10
    f2(f1(b))

    // 自定义while循环
    var i: Int = 1
    myWhile(i <= 10,{
      println(i)
      i += 1
    })

    def myWhile(condition: => Boolean, op: => Unit): Unit = {
      if (condition) {
        op
        myWhile(condition, op)
      }
    }
  }
}

