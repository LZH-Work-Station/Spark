package com.zehan.RDD.ActionOperator.ForEach

import org.apache.spark.{SparkConf, SparkContext}

object Test_01_ForEach {
  def main(args: Array[String]): Unit = {
    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("operator")
    val sc = new SparkContext(sparkConf)

    val rdd1 = sc.makeRDD(List(1, 2, 3, 4, 5, 6))
    //从计算的角度, 算子以外的代码都是在 Driver 端执行, 算子里面的代码都是在 Executor端执行。
    // 那么在 scala 的函数式编程中，就会导致算子内经常会用到算子外的数据，这样就形成了闭包的效果，
    // 如果使用的算子外的数据无法序列化，就意味着无法传值给 Executor端执行，就会发生错误，所以需要在执行任务计算前，
    // 检测闭包内的对象是否可以进行序列化，这个操作我们称之为闭包检测。


    val user = new User()
    // 如果直接foreach 没有顺序概念 分区执行的结果哪个先到就先输出谁 所以无序
    // 因为forEach是driver将任务通过网络发给executor然后让executor执行，所以object需要被序列化或者使用样例类才能执行
    rdd1.foreach(data => println(data + " " + user.age))

    rdd1.collect().foreach(
      data => println(data + " " + user.age)
    ) // collect之后会按照分区顺序进行采集 所以有序

    sc.stop()
  }

}
//case class User{    // 样例类会自动实现Serilazible接口
class User extends Serializable {
  val age: Int = 30;
}
