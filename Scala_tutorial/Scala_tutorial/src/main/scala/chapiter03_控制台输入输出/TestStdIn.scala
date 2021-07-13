package chapiter03_控制台输入输出

import chapiter01.Student

import scala.io.StdIn

object TestStdIn {
  def main(args: Array[String]): Unit = {
    println("姓名")
    var name = StdIn.readLine()
    println("年龄")
    var age = StdIn.readInt()

    var alice = new Student(name, age);
    alice.printInfo()

  }

}
