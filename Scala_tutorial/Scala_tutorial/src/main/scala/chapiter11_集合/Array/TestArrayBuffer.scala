package chapiter11_集合.Array

import scala.collection.mutable.ArrayBuffer

object TestArrayBuffer {
  def main(args: Array[String]): Unit = {
    // 1. 创建可变数组
    var arr1: ArrayBuffer[Int] = new ArrayBuffer[Int]() // 不传参的话默认大小是16
    val arr2 = ArrayBuffer(1, 2, 3, 4, 5, 6)

    // 这里的println并没有 打印地址，而是调用ArrayBuffer的toString方法
    println(arr2)

    // 2. 遍历方法和immutable列表的方法都相同

    // 3. 添加元素
    println("使用:+和+:的方式")
    // 这里的引用发生了改变，因为创建了新的数组, 不太推荐在可变数组使用 因为引用会变
    val arr3 = 46 +: arr1 :+ 45 :+ 55
    println(arr1 == arr3)

    println("使用+=的方式")
    // 这里引用没变 但是不推荐 可读性不好
    arr1 = arr1 += 100
    arr1 = 77 +=: arr1
    println(arr1)

    // 使用方法添加
    arr1.append(123)
    arr1.append(866)
    arr1.insert(0, 446)
    println(arr1)

    // 添加整个数组
    val arr4 = Array(1, 2, 3)
    arr1.appendAll(arr4)
    arr1.insertAll(3, arr4)
    arr1.prependAll(arr4)
    println(arr1)

    // 4. 删除元素
    arr1.remove(0) // 删除指定位置的元素
    arr1.remove(0, 10) // 删除从0开始的10个数
    arr1 -= 14 // 删除指定元素

    // 5. 可变数组和不可变数组的转化
    val arr = ArrayBuffer(1, 3, 5, 7, 9)
    val immutableArr = arr.toArray
    println(immutableArr.mkString(", "))
    val mutableArr = immutableArr.toBuffer
    println(mutableArr)
  }
}
