package chapiter09_面向对象.Test_01_CreatClass
import scala.beans.BeanProperty
object TestCreatPerson {
  def main(args: Array[String]): Unit = {
    val person = new Person()
    println(person.age)
    person.age = 22
    println(person.age)
  }

}

class Person{
  private var name: String = "Li Zehan"
  @BeanProperty // 自动生成java的get/set方法，因为scala中可以使用java的API，很多API需要get/set方法
  var age: Int = _
  var sex: String = "male"
}