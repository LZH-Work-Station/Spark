package chapiter11_集合.WordCount

object ComplexWordCount {
  def main(args: Array[String]): Unit = {
    // 思路二：基于预统计的结果进行转换
    val tupleList: List[(String, Int)] = List(
      ("hello world", 2),
      ("hello scala", 1 ),
      ("hello spark from scala", 3),
      ("hello flink from scala", 1)
    )

    val preCountList: List[(String, Int)] = tupleList.flatMap(
      tuple => {
        val tmp: Array[String] = tuple._1.split(" ")
//        var list: List[(String, Int)] = List()
//        for(s <- tmp){
//          list = list :+ (s, tuple._2)
//        }
//        list
        tmp.map(word => (word, tuple._2))
      }
    )
    println(preCountList)

    // 2. 按照单词进行分组
    val listGroupBy = preCountList.groupBy(tuple => tuple._1)
    println(listGroupBy)

    // 3. 规约
    val countMap = listGroupBy.mapValues(tupleList => {
      tupleList.map(_._2).sum
    })
    println(countMap)

    // 4. 转化列表并排序
    var list: List[(String, Int)] = countMap.toList
    list = list.sortWith(_._2 > _._2)
    println(list)

  }

}
