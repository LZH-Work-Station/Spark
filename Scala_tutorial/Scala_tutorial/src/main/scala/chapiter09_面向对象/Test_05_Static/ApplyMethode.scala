package chapiter09_面向对象.Test_05_Static
//（1）通过伴生对象的apply方法，实现不使用new方法创建对象。
//
//（2）如果想让主构造器变成私有的，可以在()之前加上private。
//
//（3）apply方法可以重载。
//
//（4）Scala中obj(arg)的语句实际是在调用该对象的apply方法，即obj.apply(arg)。用以统一面向对象编程和函数式编程的风格。
//
//（5）当使用new关键字构建对象时，调用的其实是类的构造方法，当直接使用类名构建对象时，调用的其实时伴生对象的apply方法。

object ApplyMethode {
  def main(args: Array[String]): Unit = {
    val t1: Teacher = Teacher("Li Zehan") // 如果调用伴生对象的apply方法 不需要 .apply() 直接类名加括号就可以
    val t2: Teacher = Teacher("Jia Yimeng", 18)
    t1.printerInfo()
    t2.printerInfo()
  }
}

class Teacher private(cName: String) {  // 主构造器被private 无法通过new创建对象
  val name: String = cName
  var age: Int = _
  def this(name: String, age: Int){
    this(name)
    this.age = age
  }
  def printerInfo(): Unit = {
    println("I am " + name)
  }
}

object Teacher{
  def apply(name: String): Teacher = {
    // 伴生类 可以直接访问class里面的所有信息包括private
    new Teacher(name)
  }
  def apply(name: String, age: Int): Teacher = {
    // apply方法的重载
    new Teacher(name, age)
  }
}
