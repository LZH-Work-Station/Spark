package chapiter09_面向对象.Test_06_Trait

object TestSameNameOfMethod {
  def main(args: Array[String]): Unit = {
    val cat: Animal = new Animal
    println(cat.describe())
  }
}

trait Color1{
  def describe(): String = {
    "Color"
  }
}

trait Category1{
  def describe(): String = {
    "Category"
  }
}
// 当继承的两个特质没有一个共同的父特质就需要重写重名方法
class Animal extends Category1 with Color1 {
  override def describe(): String = {
    "重写的"
  }
}
