package chapiter08_函数和方法

import scala.collection.mutable.ArrayBuffer

object TestEasyFunction4 {
  def main(args: Array[String]): Unit = {

    //    (1)map映射
    def map(arr: Array[Int], op: Int => Int) = {
      for (elem <- arr) yield op(elem)
    }

    val arr = map(Array(1, 2, 3, 4), (x: Int) => {
      x * x
    })
    println(arr.mkString(","))


    //    (2)filter过滤，有参数，且参数在后面只使用一次，则参数省略且后面的参数用_表示
    def filter(arr: Array[Int], op: Int => Boolean) = {
      var arr1: ArrayBuffer[Int] = ArrayBuffer[Int]()
      for (elem <- arr if op(elem)) {
        arr1.append(elem)
      }
      arr1.toArray
    }

    var arr1 = filter(Array(1, 2, 3, 4), _ % 2 == 1)
    println(arr1.mkString(","))

    //    (3)reduce聚合，有多个参数，且每个参数在后面只使用一次，则参数省略且后面参数用_表示，第n个_代表第n个参数
    def reduce(arr: Array[Int], op: (Int, Int) => Int) = {
      var init: Int = arr(0)
      for (elem <- 1 until arr.length) {
        println(elem)
        init = op(init, elem)
      }
      init
    }

    val arr2 = reduce(Array(1, 2, 3, 9), _ * _)
    println(arr2)

  }
}
