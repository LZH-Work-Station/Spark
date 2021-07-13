package chapiter11_集合.Set

import scala.collection.mutable

object MutableSet {
  def main(args: Array[String]): Unit = {
    // 1. 创建Set
    val set1: mutable.Set[Int] = mutable.Set(1, 2, 3, 4, 5) // 不可变的Set
    println(set1)

    // 2. 添加元素
    set1.add(6)
    println(set1)

    // 3. 删除元素
    set1.remove(1)
    println(set1)

    // 4. 合并两个set
    val set2 = mutable.Set(6, 7, 8, 9)
    set2 ++= set1
    println(set2)
  }
}
