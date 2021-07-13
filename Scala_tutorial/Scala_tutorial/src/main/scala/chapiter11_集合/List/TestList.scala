package chapiter11_集合.List

object TestList {
  def main(args: Array[String]): Unit = {
    // 1. 创建一个List(是链表) 是不可变的 List是抽象类 不能new 创建
    var list1 = List(1, 2, 3, 4, 5)
    println(list1)

    // 2. 获取元素, 但是不能更改指定元素
    println(list1(1))

    // 3. 遍历数据
    list1.foreach(print)

    // 4. 添加元素
    println()
    list1 = list1 :+ 6
    list1 = 0 +: list1
    println(list1)

    // 基于Nil和::的创建新List, Nill是空List 不能实现在末尾追加 只能在头部添加
    // 末尾添加可以用 :+ 或者 ::: List(要添加的元素)
    var list5 = 32 :: Nil
    list5 = 18 :: 20 :: list5
    println(list5)

    // 链表合并 :::
    var list6 = List(6, 7, 8, 9)
    list6 = list5 ::: list6
    println(list6)


  }

}
