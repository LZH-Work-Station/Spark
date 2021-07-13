package chapiter05_数据类型

object TestTransform {
  def main(args: Array[String]): Unit = {
    // (1) byte -> short -> int -> float -> double 自动提升
    val a1: Byte = 10
    val b1: Long = 20L
    // (2) 高精度转化为低精度需要 toInt toByte之类的转化
    val result1: Int = (a1 + b1).toInt
    val result2: Byte = result1.toByte
    // (3) char -> int 转化为ASCII码
    val char: Char = 'a'
    val charInt: Int = char
    println(charInt)
    // (4) Byte Short作运算 结果会自动转化为Int
    val short: Short = 10;
    val byte: Byte = 20;
    val int: Int = short + byte
    // (5) String to Int
    val num: String = "123"
    val num1: Int = num.toInt
    println(num1)

  }

}
