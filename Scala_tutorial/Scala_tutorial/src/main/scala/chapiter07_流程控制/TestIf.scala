package chapiter07_流程控制

object TestIf {
  def main(args: Array[String]): Unit = {
    // 当把一个表达式封装在一个变量中，这个变量会获取最后一行代码
    // 类似于三元运算符
    var result = if (1>0) 1 else 0
    println(result)
  }

}
