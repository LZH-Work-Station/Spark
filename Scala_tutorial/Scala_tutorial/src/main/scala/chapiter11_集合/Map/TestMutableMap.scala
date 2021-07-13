package chapiter11_集合.Map

import scala.collection.mutable


object TestMutableMap {
  def main(args: Array[String]): Unit = {

    //可变集合
    val map = mutable.Map("a" -> 1, "b" -> 2, "c" -> 3)
    //增加数据
    map.put("g", 7)
    println("map:", map)

    val map2: Option[Int] = map.put("a", 4)
    println(map2.getOrElse(0))
    println("map2:", map2)

    //删除数据
    map.-=("a", "b")
    println(map)
    map.remove("c")
    println(map)

    //修改数据
    map.update("d", 44)
    println(map)
    map("g") = 5
    println(map)

  }
}
