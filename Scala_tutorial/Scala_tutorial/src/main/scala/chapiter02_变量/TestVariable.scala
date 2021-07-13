package chapiter02_变量

import chapiter01.Student

object TestVariable {
  def main(args: Array[String]): Unit = {
    // (1) 声明时类型可以省略
    // (2) 类型确定后不可更改类型
    // (3) 声明变量需要有初始值
    // (4) 引用类型如果用val定义则不可再重新定义，但是里面的属性如果是var可以被更改（即使 用val实例类）

    // 声明变量
    var a: Int = 10
    // 声明的变量类型可以省略, 编译器会自动对变量进行类型推断
    var b = 10
    // 声明常量 相当于final
    val constant = 500

    val alice = new Student("alice", 20)
    alice.age = 21
  }

}
