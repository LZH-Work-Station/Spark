package com.zehan.RDD.TransformOperator.ByKeyOperation.foldByKey

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.rdd.RDD

object Test_01_foldByKey {
  def main(args: Array[String]): Unit = {
    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("operator")
    val sc = new SparkContext(sparkConf)

    val rdd: RDD[(String, Int)] = sc.makeRDD(List(("a", 1), ("a", 1), ("b", 2), ("b", 3), ("a", 4)))

    // 相比于aggregate，这个foldByKey是分区间和分区内的运算方法完全相同
    rdd.foldByKey(0)(_+_).collect().foreach(println)
  }
}
