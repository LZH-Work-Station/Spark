package chapiter07_流程控制

object TestFor {
  def main(args: Array[String]): Unit = {
    // (1) 0 to 9 相当于range(10)
    for(i <- 0 to 9){
      println(i + "Hello World")
    }
    // (2) 如果是右边不包含
    for(i <- 0 until 10){
      println(i + "Hello World")
    }
    // (3) 本质上 to和until都是调用Range类的伴生对象的apply方法
    for(i <- Range(0, 10)){
      println(i + "调用Range的apply方法")
    }
    // (4) for each的感觉，遍历集合里面的元素
    for(i <- Array(12, 34, 62)){
      println(i)
    }
    // (5) 跳过某个条件的循环 代替continue
    for(i <- 0 to 9 if(i != 9)){
      println(i)
    }
    // (6) 定义步长
    for(i <- 9 to 0 by -1){
      print(i + " ")
    }
    println()
    // (7) 二维数组的for循环遍历
    for(i <- 0 to 4; j <- 0 to 4){
      print("(" + i + ", " + j + ")")
    }
    println()
    // (8) yield返回一个集合
    var res = for(i <- 1 to 10) yield i*2
    println(res)
  }
}
