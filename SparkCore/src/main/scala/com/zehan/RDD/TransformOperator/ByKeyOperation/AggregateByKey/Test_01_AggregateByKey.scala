package com.zehan.RDD.TransformOperator.ByKeyOperation.AggregateByKey

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.rdd.RDD

object Test_01_AggregateByKey {
  def main(args: Array[String]): Unit = {
    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("operator")
    val sc = new SparkContext(sparkConf)

    val rdd: RDD[(String, Int)] = sc.makeRDD(List(("a", 1), ("b", 1), ("a", 2), ("a", 3), ("b", 4)), 2)

    rdd.saveAsTextFile("output")
    // 存在函数柯里化，有两个参数列表
    // 第一个参数列表 是初始值，比如下面的math.max的第一个数据计算的时候，如果没有初始值没法做比较
    // 第二个参数列表需要传递两个参数
    //    第一个参数表示分区规则
    //    第二个参数表示分区间计算规则
    val rddAggregate: RDD[(String, Int)] = rdd.aggregateByKey(Int.MinValue)(
      (x, y) => math.max(x, y), // 分区规则是取每个分区的最大值
      (x, y) => x + y // 分区间计算规则是将最大值求和
    )

    // 获取相同Key数据的 (0, 0)作为初始值，第一位代表和第二位代表数量
    // (t, v)中t就代表我们一直迭代的tuple,即第一位代表总和，第二位代表数量，v代表和这个tuple相同key的下一个数据
    val newRdd: RDD[(String, (Int, Int))] = rdd.aggregateByKey((0, 0))(
      (t, v) => {
        (t._1 + v, t._2 + 1)
      },
      (t1, t2) => {
        (t1._1 + t2._1, t1._2 + t2._2)
      }
    )

    newRdd.mapValues{
      case(num, count) => {
        num/count
      }
    }.collect().foreach(println)

    rddAggregate.collect().foreach(println)

  }
}
