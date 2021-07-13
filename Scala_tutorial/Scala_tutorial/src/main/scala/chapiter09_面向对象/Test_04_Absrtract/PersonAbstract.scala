package chapiter09_面向对象.Test_04_Absrtract
//（1）定义抽象类：abstract class Person{} //通过abstract关键字标记抽象类
//
//（2）定义抽象属性：val|var name:String //一个属性没有初始化，就是抽象属性

// (3）定义抽象方法：def hello():String //只声明而没有实现的方法，就是抽象方法

// ===================继承和重写================================

//（1）如果父类为抽象类，那么子类需要将抽象的属性和方法实现，否则子类也需声明为抽象类
//
//（2）重写非抽象方法需要用override修饰，重写抽象方法则可以不加override。
//
//（3）子类中调用父类的方法使用super关键字
//
//（4）子类对抽象属性进行实现，父类抽象属性可以用var修饰； 子类对非抽象属性重写，父类非抽象属性只支持val类型，而不支持var。
//     因为var修饰的为可变变量，子类继承之后就可以直接使用，没有必要重写
//
//（5）Scala中属性和方法都是动态绑定，而Java中只有方法为动态绑定。

// 在抽象类里面得主构造方法中也可以定义必须被继承的属性
abstract class PersonAbstract(name: String) {
  var profile: String
  def printerInfo(): Unit
}

class Teacher(name: String) extends PersonAbstract(name) {
  var profile: String = _ //重写需要被继承的抽象属性

  def this(name: String, profile: String) {
    this(name) // 调用父类的主构造方法
    this.profile = profile // 给实例化的抽象属性赋值
  }
  override def printerInfo(): Unit = { // 重写抽象方法
    println(s"My name is $name, I am a $profile")
  }
}

object Test{
  def main(args: Array[String]): Unit = {
    val t1: Teacher = new Teacher("Li Zehan", "teacher")
    val t2: PersonAbstract = new Teacher("Li Zehan", "teacher")
    println(t2.profile) // 在scala中属性也是动态绑定，即使多态使用PersonAbstract当作变量类型也能获取Teacher的profile属性
    t1.printerInfo()
  }
}