package chapiter01

// class 编译后放在正常的class文件中
class Student(var name: String, var age: Int) {
  def printInfo(): Unit = {
    println(name + " " + age + " " + Student.School)
  }
}

// 伴生对象object编译后放在dollar文件中
// 伴生对象，保存类的静态信息，即把所有对象共享的信息放在object即伴生对象中
// 伴生类和主类里面的信息可以互相用
object Student {
  val School: String = "新华"

  def main(args: Array[String]): Unit = {
    val alice = new Student("alice", 20)
    val bob = new Student("bob", 21)
    alice.printInfo()
    bob.printInfo()
  }
}
