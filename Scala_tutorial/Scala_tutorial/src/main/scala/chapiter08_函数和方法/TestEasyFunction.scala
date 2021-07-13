package chapiter08_函数和方法

object TestEasyFunction {
  def main(args: Array[String]): Unit = {
    def f(s: String): String = {
      return s + "f"
    }

    println(f("f"))
    println("-------------------")

    //1.return可以省略，scala会使用函数体的最后一行代码作为返回值
    def f1(s: String): String = {
      s + "f1"
    }

    println(f1("f1"))
    println("-------------------")

    //2.如果函数体只有一行代码，可以省略花括号
    def f2(s: String): String = s + "f2"

    println(f2("f2"))
    println("-------------------")

    //3.返回值类型如果能够推断出来，那么可以省略
    def f3(s: String) = s + "f3"

    println(f3("f3"))
    println("-------------------")

    //4.如果有return，则不能省略返回值类型，必须指定
    def f4(): String = {
      return "f4"
    }

    println(f4())
    println("-------------------")

    //5.如果函数明确声明unit，那么即使函数体中使用return关键字也不起作用
    def f5(): Unit = {
      return "f5"
    }

    println(f5())
    println("-------------------")

    //6.如果期望是无返回值类型，那么可以省略等号和Unit
    def f6() {
      println("f6")
    }

    println(f6())
    println("-------------------")

    //7.如果函数无参，但是声明了参数列表，那么调用时，小括号，可加可不加
    def f7() = "f7"

    println(f7())
    println(f7)
    println("-------------------")

    //8.如果函数没有参数列表，那么小括号可以省略，调用时小括号必须省略
    def f8 = "f8"

    println(f8)
    println("-------------------")

    //9. 匿名函数。只有参数和output 即lambda表达式
    (name: String) => {println("f9 " +  name)}
    // 定义一个以函数为参数的函数 然后使用匿名函数作为参数用
    def Test(func: String => Unit): Unit = {
      func("Test 匿名方法")
    }
    // 匿名函数作为参数可以省略 参数类型
    Test(name => {println("f9 " +  name)})
    // 匿名函数作为参数如果只有一个参数或者一行代码小括号或者大括号
    Test(name => println("f9 " +  name))
    // 匿名函数如果参数只出现一次，则参数省略且后面的参数用_代替
    Test(println(_))


    println("-------------------")





  }
}
