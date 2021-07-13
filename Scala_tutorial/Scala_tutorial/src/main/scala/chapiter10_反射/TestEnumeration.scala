package chapiter10_反射

object TestEnumeration {
  def main(args: Array[String]): Unit = {
    println(WorkDay.MONDAY)
  }
}

// 定义静态枚举对象
object WorkDay extends Enumeration{
  // 前面的数字就是一个id 后面是value
  val MONDAY = Value(1, "Monday")
  val TUESDAY = Value(2, "Tuesday")
}
