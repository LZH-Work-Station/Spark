package chapiter10_反射

object TestExtends {
  def main(args: Array[String]): Unit = {
    // 1. 类型的检测和转换, 具体是什么类型是根据 new 后面的类型决定的, 方法和属性都看左面 而不是new后面的
    val s: Student = new Student("Li Zehan", 17)
    val p: Person = new Student("Jia Yimeng", 18)
    val p2: Person = new Person("123", 123)
    // 只能调用被继承的重写的方法, 再Student里面的新方法 无法被p调用
    p.sayHi()
    s.sayHi()

    println("student is Person: " + s.isInstanceOf[Person])
    println("Person(Student) is Student: " + p.isInstanceOf[Student])
    println("Person(Person) is Student: " + p2.isInstanceOf[Student])
    // 2. 类型转化
    if(p.isInstanceOf[Student]){
      val newStudent = p.asInstanceOf[Student]
      newStudent.study()
    }
    // 3. 获得变量的类型
    println(classOf[Person])


  }
}

class Person(var name: String, var age: Int){
  def sayHi(): Unit = {
    println("Hi from Person " + name)
  }
}

class Student(name: String, age: Int) extends Person(name, age){
  override def sayHi(): Unit = {
    println("Hi from Student " + name)
  }

  def study(): Unit = {
    println("Study")
  }
}

