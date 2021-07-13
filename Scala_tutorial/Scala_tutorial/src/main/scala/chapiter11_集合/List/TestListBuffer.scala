package chapiter11_集合.List

import scala.collection.mutable.ListBuffer

object TestListBuffer {
  def main(args: Array[String]): Unit = {
    // 1. 创建ListBuffer
    var list1 = new ListBuffer[Int]()
    var list2 = ListBuffer(1, 2, 3)

    // 2. 添加元素
    list1.append(4, 5)
    list2.prepend(0)

    list2.insert(4, 4)
    println(list2)

    -1 +=: list2 += 5
    println(list2)

    // 3. 合并list "++=" ，在前面添加用 添加的列表 ++=: 被添加列表
    list2 ++= list1
    list2 ++=: list1
    println(list2)
    println(list1)

    // 4. 删除元素
    list1.remove(0) // 删除位置0的数字
    list1 -= 5  // 删除从左往右数第一个 值5的元素

    println(list1)

  }
}
