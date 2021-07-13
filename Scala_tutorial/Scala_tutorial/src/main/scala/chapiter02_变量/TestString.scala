package chapiter02_变量

object
TestString {
  def main(args: Array[String]): Unit = {
    // (1) 字符串拼接
    println("第一句" + "第二句")

    // (2) printf用法， 占位符
    var age = 10;
    printf("我今年%d岁了", age)
    println()

    // (3) 字符串模板 通过$获取变量值
    println(s"我今年${age}岁了")

    // (4) 小数保留位数
    var num = 2.345;
    println(f"我今年${num}%.2f岁了")

    // (5) 多行字符串
    var sql = s"""
      |select *
      |from student
      |where age > ${age}
      |""".stripMargin

    println(sql)

  }

}
