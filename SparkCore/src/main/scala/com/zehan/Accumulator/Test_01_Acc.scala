package com.zehan.Accumulator

import org.apache.spark.rdd.RDD
import org.apache.spark.util.LongAccumulator
import org.apache.spark.{SparkConf, SparkContext}

object Test_01_Acc {
  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("WordCount")
    val sc = new SparkContext(sparkConf)

    val rdd: RDD[Int] = sc.makeRDD(List(1, 2, 3, 4))

    // 因为是分布式的累加，而且每一个算子并没有从 executor返回到driver的 路径 所以 我们不能简单的定义一个变量去做累加
    // 累加器用来吧executor端变量信息聚合到driver端，在driver程序中定义的变量
    // 会在executor端的每个Task都会得到这个变量的一份新的副本，每个task更新这些副本后
    // 传回driver端进行merge

    // TODO 获取系统累加器
    val sumAcc: LongAccumulator = sc.longAccumulator("sum")
    val sumAcc1: LongAccumulator = sc.longAccumulator("sum1")

    rdd.foreach(
      num => {
        sumAcc.add(num)
      }
    )
    // 少加：map是转换算子，如果没有行动算子就不会执行
    // 多加：
    val mapRDD: RDD[Int] = rdd.map(
      num => {
        // 使用累加器
        sumAcc1.add(num)
        num
      }
    )

    println(sumAcc.value)
    println("Collect 之前 ： ")
    println(sumAcc1.value)
    println("Collect 之后 ： ")
    mapRDD.collect()
    println(sumAcc1.value)

    sc.stop()
  }
}
