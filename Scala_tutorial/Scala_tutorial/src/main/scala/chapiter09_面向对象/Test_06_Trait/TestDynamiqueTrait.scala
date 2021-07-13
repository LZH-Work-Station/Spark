package chapiter09_面向对象.Test_06_Trait
//（4）动态混入：可灵活的扩展类的功能
//
//（4.1）动态混入：创建对象时混入trait，而无需使类混入该trait
//
//（4.2）如果混入的trait中有未实现的方法，则需要实现
object TestDynamiqueTrait{
  def main(args: Array[String]): Unit = {
    val t1 = new Teacher("Li Zehan", 18, "teacher") with sexTrait{
      override var sex: String = "男"
    }
    println(t1.describle)
    println(t1.sex)
  }
}

trait PersonTrait{
  var name: String
  var age: Int
}

trait profession{
  var profession: String
  def describle: String
}

trait sexTrait{
  var sex: String
}

class Teacher extends PersonTrait with profession{
  override var name: String = _
  override var age: Int = _
  override var profession: String = _

  def this(name: String, age: Int, profession: String){
    this()
    this.name = name
    this.age = age
    this.profession = profession
  }

  override def describle: String = {
    s"My name is $name, I am $age years old, I am a $profession"
  }
}
