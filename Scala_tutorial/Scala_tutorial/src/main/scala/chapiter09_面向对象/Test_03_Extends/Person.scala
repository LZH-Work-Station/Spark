package chapiter09_面向对象.Test_03_Extends

//（1）基本语法
//
// class 子类名 extends 父类名 { 类体 }
//
//（1）子类继承父类的属性和方法
//
//（2）scala是单继承

class Person(nameParam: String) { // 主构造器
  var name = nameParam
  var age: Int = _ // 给属性一个默认值

  def this(nameParam: String, ageParam: Int) {
    this(nameParam)
    this.age = ageParam
    println("父类辅助构造器")
  }

  println("父类主构造器")
}

// 继承的时候要带有父类的一种构造方式一起继承，这里继承的辅助构造器
class Emp(nameParam: String, ageParam: Int) extends Person(nameParam, ageParam) {
  var empNo: Int = _

  def this(nameParam: String, ageParam: Int, empNoParam: Int) {
    this(nameParam, ageParam)
    this.empNo = empNoParam
    println("子类的辅助构造器")
  }

  println("子类的主构造器")
}


object Test01 {
  def main(args: Array[String]): Unit = {
    new Emp("z3",11,1001)
  }
}
