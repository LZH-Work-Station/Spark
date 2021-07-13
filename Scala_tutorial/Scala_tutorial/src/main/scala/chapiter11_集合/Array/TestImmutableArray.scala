package chapiter11_集合.Array

object TestImmutableArray {
  // Array通过隐式转换 转换成WrappedArray然后扩展List特征
  def main(args: Array[String]): Unit = {
    // 1. 创建不可变数组
    val arr: Array[Int] = new Array[Int](10)
    // 另一种创建方式, 直接对Array赋值，调用的是apply方法
    var arr2: Array[Int] = Array(1, 2, 3, 4, 5)

    // 2. 遍历
    // (1) for each
    println("For取出所有元素")
    arr2(4) = 178
    for (i <- arr2) {
      print(i + ", ")
    }
    println("")

    // (2) 正常的for 0到最后
    println("For 使用索隐遍历")
    for (i <- arr2.indices) {
      print(arr2(i) + ", ")
    }
    println("")

    // (3) 指定范围
    println("For 指定范围")
    for (i <- 1 to 4) {
      print(arr2(i) + ", ")
    }
    println("")

    // (4) iterator
    println("Iterator")
    val it: Iterator[Int] = arr2.iterator
    while (it.hasNext) {
      print(it.next() + ", ")
    }
    println("")

    // (4) foreach 方法，foreach里面传的是function函数 lambda表达式
    println("foreach 方法， 不用<-")
    arr2.foreach((elem: Int) => print(elem + ", "))
    // 或者直接
    arr2.foreach(println)

    // (5) 优化输出, mkString
    println("mkString方法")
    println(arr2.mkString("--"))

    // (6) 添加元素，本质是创建一个新数组 跟java中的String一样
    arr2 = arr2.:+(56) // 添加到末尾
    arr2 = arr2.+:(30) // 添加到开头
    arr2 = arr2 :+ 15 // 简化版添加到结尾
    arr2 = 29 +: arr2 // 简化版添加到开头
    println(arr2.mkString("--"))
  }

}
