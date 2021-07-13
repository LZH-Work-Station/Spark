package chapiter09_面向对象.Test_04_Absrtract

abstract class AnonymeAbstractTeacher {
  var name: String
  def printerInfo: Unit
}

object Test01{
  def main(args: Array[String]): Unit = {
    // 创建的匿名子类 没有声明一个新的类
    val t: AnonymeAbstractTeacher = new AnonymeAbstractTeacher {
      override var name: String = "Li Zehan"
      var profile: String = "Teacher"

      override def printerInfo: Unit = {
        println(s"I am $name, I am a $profile")
      }
    }
    t.printerInfo
  }
}
