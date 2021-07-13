package chapiter11_集合.Function


object TestListFunction01 {

  def main(args: Array[String]): Unit = {

    val list = List(1, 2, 3, 4, 5, 6, 7)


    //    1.获取集合长度
    println(list.length)

    //    2.获取集合大小，等同于length
    println(list.size)

    //    3.循环遍历
    list.foreach(println)

    //    4.迭代器
    for (elem <- list.iterator) {
      println(elem)
    }

    //    5.生成字符串
    println(list.mkString(","))

    //    6.是否包含
    println(list.contains(3))

    // ---------------------------------------------------------------------------------
    val list1 = List(1, 2, 3, 4, 5, 6, 7)
    val list2 = List(4, 5, 6, 7, 8, 9, 10)
    //    1.获取集合的头
    println(list1.head)

    //    2.集合最后一个数据
    println(list1.last)

    //    3.选择除第一个元素剩下的所有元素
    println(list1.tail)

    //    4.选择除最后一个元素剩下的所有元素
    println(list1.init)

    //    5.反转
    println(list1.reverse)

    //    6.取前、后几个元素
    println(list1.take(3))
    println(list1.takeRight(3))

    //    7.去掉前、后几个 元素
    println(list1.drop(3))
    println(list1.dropRight(3))

    //    8.并集
    println(list1.union(list2))

    //    9.差集
    println(list1.diff(list2))

    //    10.交集
    println(list1.intersect(list2))

    //    11.拉链 形成元组 注:如果两个集合的元素个数不相等，那么会将同等数量的数据进行拉链，多余的数据省略不用
    println(list1.zip(list2))

    //    12.滑窗 size大小为3, step为2，sliding返回的是一个iterator
    list1.sliding(3, 2).foreach(println)

  }
}
