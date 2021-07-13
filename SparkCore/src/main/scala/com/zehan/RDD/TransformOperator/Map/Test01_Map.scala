package com.zehan.RDD.TransformOperator.Map

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Test01_Map {
  def main(args: Array[String]): Unit = {
    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("operator")
    val sc = new SparkContext(sparkConf)

    val rdd = sc.makeRDD(List(1, 2, 3, 4))

    // 转换函数
    def mapFunction(num: Int): Int = {
      num * 2
    }

    val mapRDD: RDD[Int] = rdd.map(mapFunction)

    // 最简化是
    // val mapRDD = rdd.map(_*2)

    mapRDD.collect().foreach(println)

    sc.stop()
  }
}
