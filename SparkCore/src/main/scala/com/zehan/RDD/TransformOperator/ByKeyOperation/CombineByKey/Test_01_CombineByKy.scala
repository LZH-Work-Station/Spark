package com.zehan.RDD.TransformOperator.ByKeyOperation.CombineByKey

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Test_01_CombineByKy {
  def main(args: Array[String]): Unit = {
    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("operator")
    val sc = new SparkContext(sparkConf)

    val rdd: RDD[(String, Int)] = sc.makeRDD(List(("a", 1), ("a", 1), ("b", 2), ("b", 3), ("a", 4)))

    // combineByKey : 三个参数
    // 第一个参数：将相同key的第一个数据的数据结构进行转换
    // 第二个参数： 分区内的计算规则
    // 第三个参数： 分区间的计算规则

    // 实现相同key的平均值计算
    val newRDD: RDD[(String, (Int, Int))] = rdd.combineByKey(
      v => (v, 1), // 第一个数据结构变成了元组 (v, 1) 1代表个数，v代表数据和
      (tuple: (Int, Int), v) => (tuple._1 + v, tuple._2 + 1),
      (tuple1: (Int, Int), tuple2: (Int, Int)) => (tuple1._1 + tuple2._1, tuple1._2 + tuple2._2)
    )

    val resRdd: RDD[(String, Double)] = newRDD.mapValues {
      case (sum, count) => {
        sum / count.toDouble
      }
    }
    resRdd.collect().foreach(println)

    sc.stop()
  }
}
