package chapiter10_反射
// 只是省略了一个main方法
object TestApp extends App{
  println("我是一个APP")
  // 给String重新起名叫MyString
  type MyString = String

  val a: MyString = "abc"
  println(a)
}



