package chapiter11_集合.Function

object TestListFunction02 {
  def main(args: Array[String]): Unit = {
    val list: List[Int] = List(1, 5, -3, 4, 2, -7, 6)
    val list2 = List(("a", 2), ("b", 1))

    //    1.求和
    println(list.sum)

    //    2.求乘积
    println(list.product)

    //    3.最大值
    println(list.max)
    println(list2.maxBy(tuple => tuple._2))

    //    4.最小值
    println(list.min)

    //    5.排序 Ordering是一个隐式参数，相当于对应类的规则，有点像Comparator，这里代表Int的排序规则
    println(list.sorted(Ordering[Int]))
    //    5.1按照元素大小排序
    println(list.sortBy(x => x))

    //    5.2按照元素的绝对值大小排序
    println(list.sortBy(x => x.abs))

    //    5.3按照元素大小升序排序 comparator
    println(list.sortWith((x, y) => x < y))

    //    5.4按元素大小降序排序
    println(list.sortWith((x, y) => x > y))
  }
}