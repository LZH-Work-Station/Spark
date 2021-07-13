package chapiter09_面向对象.Test_05_Static

object ChinesePeople {
  val country: String = "China"
}

class ChinesePeople(cName: String){
  var name = cName
  var age: Int = _
  def this(name: String, age: Int){
    this(name)
    this.age = age

  }
}

object Test02{
  def main(args: Array[String]): Unit = {
    val p1: ChinesePeople = new ChinesePeople("Li Zehan")
    println(s"My name is ${p1.name}, I am from ${ChinesePeople.country}")
    println(p1.name)
  }
}
