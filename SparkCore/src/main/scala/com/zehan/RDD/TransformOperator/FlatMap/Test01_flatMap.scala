package com.zehan.RDD.TransformOperator.FlatMap

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Test01_flatMap {
  def main(args: Array[String]): Unit = {
    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("operator")
    val sc = new SparkContext(sparkConf)

    val rdd = sc.makeRDD(List(List(1, 2, 3, 4), 3, List(5, 6, 7, 8)))


    // flatMap要求返回一个迭代器，然后她会把迭代器里面的数据提取出来做扁平化

    val flatRDD: RDD[Any] = rdd.flatMap {
      case num: Int => List(num)
      case list: List[_] => list
    }
    println(flatRDD.collect().mkString(", "))
    sc.stop()
  }
}
