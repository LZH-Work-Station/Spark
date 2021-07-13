package chapiter09_面向对象.Test_02_Encapsulation

class Student(var name: String) {  // 在类名后面的是“主构造器”， 所有的辅助构造器必须对主构造器里面的参数进行赋值
  private val classId: Int = 3
  var age: Int = _
  var studentId: String = _

  def this(name: String, age: Int, studentId: String){  // 辅助构造器
    this(name)
    this.age = age
    this.studentId = studentId
  }



  override def toString(): String = {
    "My Name is " + name + ", I am " + age + ", My classId is " + classId
  }
}

object Student{
  def main(args: Array[String]): Unit = {
    // val不能改变对象的引用（即内存地址），可以改变对象属性。 大多数情况就用val定义对象就行
    val s1: Student = new Student("Li Zehan", 18, "00001")
    val s2: Student = new Student("Jia Yimeng", 18, "00002")
    println(s1.toString())
    println(s2.toString())
  }
}
