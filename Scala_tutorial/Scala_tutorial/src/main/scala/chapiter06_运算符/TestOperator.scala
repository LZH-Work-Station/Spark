package chapiter06_运算符

object TestOperator {
  def main(args: Array[String]): Unit = {
    var n1 : Int = 10;
    var n2 : Int = 20;
    // 再scala中，运算符相当于AnyValue类型的一种方法
    // 只不过我们把.和括号进行了省略
    var n3 = n1.+(n2)
  }

}
