package com.zehan.RDD.TransformOperator.SortBy

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.rdd.RDD

object Test_01_SortBy {
  def main(args: Array[String]): Unit = {
    // TODO 准备环境
    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("RDD")
    val sc = new SparkContext(sparkConf)

    // TODO 创建RDD
    val rdd: RDD[(String, Int)] = sc.makeRDD(List(("1", 1), ("8", 9), ("11", 5), ("2", 3)), 2)

    // TODO 排序 按照Key排序
    // false 代表降序， 默认为升序
    val newRdd: RDD[(String, Int)] = rdd.sortBy(_._1.toInt, false)

    newRdd.collect().foreach(println)
    // TODO 关闭环境
    sc.stop()
  }
}
