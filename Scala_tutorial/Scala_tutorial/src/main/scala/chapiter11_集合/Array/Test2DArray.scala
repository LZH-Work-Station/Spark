package chapiter11_集合.Array

object Test2DArray extends App {
  // 1. 创建2维数组
  val arr = Array.ofDim[Int](3, 3)

  // 2. 访问元素
  arr(0)(2) = 5
  arr(1)(0) = 4
  println(arr(0)(2))

  // 3. 遍历
  println()
  for (i <- Range(0, arr.length); j <- Range(0, arr(i).length)) {
    print(arr(i)(j) + "\t")
    if (j == arr(i).length - 1) println()
  }


}
