package chapiter11_集合.Function

import scala.collection.mutable

object TestListFunction03{
  def main(args: Array[String]): Unit = {

    val list1 = List(1, 2, 3, 4, 5, 6, 7, 8, 9)
    // 1. 过滤 选取偶数
    val filterList1 = list1.filter(_%2==0)
    println(filterList1)

    // 2. 映射操作 map
    // 把集合中每个数 * 2
    println(list1.map(_*2))

    // 3. 扁平化 即将多个List中的内容拆出来然后放进一个List中
    val nestedList = List(List(1, 2, 3), List(4, 5, 6), List(7, 8, 9))
    // 老方法
    val flatList = nestedList(0) ::: nestedList(1) ::: nestedList(2)
    println(flatList)
    // 调用函数
    val flatList2 = nestedList.flatten
    println(flatList2)

    // 4. 扁平映射
    // 拆开操作
    val strings: List[String] = List("hello world", "hello scala")
    val splitList: List[Array[String]] = strings.map(_.split(" "))
    val flattenString = splitList.flatten

    println(flattenString)
    // flatMap 方法
    val flattenString2 = strings.flatMap(_.split(" "))
    println(flattenString2)

    // 5. groupBy 操作 groupBy 就是传入一个函数，这个函数是把input映射到key
    val groupMap = list1.groupBy[String](data => {
      if(data%2==0) "偶数"
      else "奇数"
    })
    println(groupMap)

    val list2 = List("china", "america", "canada", "france")
    println(list2.groupBy(_.charAt(0)))

    // 6. reduce 归约操作 每个元素叠加
    println(list1.reduce(_ - _))
    // 前面的_是规约状态
    println(list1.reduceRight(_ - _))

    // 7. fold 聚合 20是初始值，然后从初始值开始往下操作，这里是20 - list中的每一个元素
    println(list1.fold(20)(_ - _))
    val map1 = Map("a" -> 1, "b" -> 2, "c" -> 3)
    val map2 = mutable.Map("a" -> 6, "b" -> 7, "c" -> 8, "d" -> 9)

    val map3 = map1.foldLeft(map2)({
      (mergeMap, kv) => {
        val key = kv._1
        println(key)
        val value = kv._2
        mergeMap(key) = mergeMap.getOrElse(key, 0) + value
        mergeMap
      }
    })
    println(map3)


  }
}
