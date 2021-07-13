package chapiter08_函数和方法

object TestEasyFunction3 {
  def main(args: Array[String]): Unit = {
    // 将对数组的操作抽象出来, 返回一个新的数组
    def arrayOperation(array: Array[Int], op: Int => Int): Array[Int] ={
      for(elem <- array) yield op(elem)
      array
    }

    println(arrayOperation(Array(1, 2, 3, 4, 5), (a: Int) => 2 * a).mkString(","))
  }
}
