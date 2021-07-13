package com.zehan.RDD.TransformOperator.RDDCombination

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.rdd.RDD

object Test_01_Combination {
  def main(args: Array[String]): Unit = {
    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("operator")
    val sc = new SparkContext(sparkConf)

    val rdd1 = sc.makeRDD(List(1, 2, 3, 4, 5))
    val rdd2: RDD[Int] = sc.makeRDD(List(3, 4, 5, 6, 7))

    // 交集 3, 4, 5
    val rddInter: RDD[Int] = rdd1.intersection(rdd2)
    println(rddInter.collect().mkString(", "))

    // 并集 1, 2, 3, 4, 5, 3, 4, 5, 6, 7
    val rddUnion: RDD[Int] = rdd1.union(rdd2)
    println(rddUnion.collect().mkString(", "))

    // 差集 1, 2
    val rddSubtract: RDD[Int] = rdd1.subtract(rdd2)
    println(rddSubtract.collect().mkString(", "))

    // 拉链 (1,3), (2,4), (3,5), (4,6), (5,7)
    // 拉链操作可以让数据源的类型不一致， 但是如果两rdd的partition不相同 不能拉链
    val rddZip: RDD[(Int, Int)] = rdd1.zip(rdd2)
    println(rddZip.collect().mkString(", "))

    sc.stop()
  }
}
