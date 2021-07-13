package chapiter11_集合.Tuple

object TestTuple {
  def main(args: Array[String]): Unit = {
    //声明元组的方式：（元素1，元素2，元素3）
    val tuple: (Int, String, Boolean) = (20, "bobo", true)

    //访问元组
    //通过元素的顺序进行访问，调用方式:_顺序号
    println(tuple._1)   // 指第一个元素 => 20
    println(tuple._2)   // 第二个元素 => "bobo"

    //通过索引访问数据
    println(tuple.productElement(2))

    //通过迭代器访问数据
    for (e <- tuple.productIterator) {
      println(e)
    }

    //Map中的键值对其实就是元组，只不过元组的元素个数为2，称之为对偶
    val map = Map("a" -> 1, "b" -> 2, "c" -> 3)
    val map1 = Map(("a" -> 1), ("b" -> 2))
    map.foreach(tuple => {
      println(tuple._1 + "=" + tuple._2)
    })
    map1.foreach(
      println(_)
    )
  }
}



