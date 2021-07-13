package chapiter01

// object关键字生成一个单例对象，相当于静态类，public static void main这种感觉
object HelloWorld {
  // 实现 main 方法
  def main(args: Array[String]): Unit = {
    println("HelloWorld")
    System.out.println("HelloWorld")
  }
}
