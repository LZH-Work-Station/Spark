package chapiter11_集合.WordCount

object CommonWordCount {
  def main(args: Array[String]): Unit = {
    val stringList: List[String] = List(
      "hello world",
      "hello scala",
      "hello spark from scala",
      "hello flink from scala"
    )

    // 1. 对字符串进行切分
    val wordlist: List[String] = stringList.flatMap(_.split(" "))
    println(wordlist)

    val groupStrings = wordlist.groupBy( word => word )
    println(groupStrings)

    val groupMap = groupStrings.map(kv => (kv._1, kv._2.length))
    println(groupMap)

    val sortList = groupMap.toList.sortWith(_._2 > _._2)
    println(sortList)
  }
}
