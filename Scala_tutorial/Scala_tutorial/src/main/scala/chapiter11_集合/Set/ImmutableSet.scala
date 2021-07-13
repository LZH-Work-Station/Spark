package chapiter11_集合.Set

object ImmutableSet {
  def main(args: Array[String]): Unit = {
    // 1. 创建Set
    var set1 = Set(5, 4, 3, 2, 2, 1)
    println(set1)

    // 2. 添加元素
    set1 = set1 + 20
    println(set1)

    // 3. 合并集合
    var set2 = Set(10, 9, 6, 4)
    set2 ++= set1
    println(set2)

    // 4. 删除元素
    set2 = set2 - 20
    println(set2)
  }

}
