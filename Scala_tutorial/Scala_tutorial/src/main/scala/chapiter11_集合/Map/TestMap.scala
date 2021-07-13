package chapiter11_集合.Map

object TestMap {
  def main(args: Array[String]): Unit = {
    //    1.创建不可变集合Map
    val map = Map("a" -> 1, "b" -> 2, "c" -> 3)
    println(map)

    //遍历
    map.foreach(println)

    map.foreach((kv) => {
      println(kv)
    })
    map.foreach((kv: (String, Int)) => println(kv))

    //访问数据
    for (elem <- map.keys) {
      //      使用get访问map集合的数据，会返回特殊类型Option(选项)：有值（Some），无值（None）
      println(elem + "=" + map(elem))
    }

    //访问具体数据
    println(map.get("a")) // get获得的是Some类型，是具体值的一个包装类，为了防止空指针异常（没有对应的key）
    println(map.get("a").get) // 获得具体的值
    println(map("a")) // 最简单的方法

    // println(map("c")) // 空指针异常

    //4.如果key不存在，返回0
    println(map.getOrElse("d", 0))
  }

}
