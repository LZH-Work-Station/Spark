package com.zehan.RDD.Create

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Test02_RDD_File {
  def main(args: Array[String]): Unit = {
    // TODO 准备环境
    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("RDD")
    val sc = new SparkContext(sparkConf)

    // TODO 创建RDD
    val rdd: RDD[String] = sc.textFile("SparkCore/src/main/resources")

    // TODO 直到数据源的RDD
    // 以元组的形式储存数据(路径， 数据)
    val rdd2: RDD[(String, String)] = sc.wholeTextFiles("SparkCore/src/main/resources")
    rdd2.collect().foreach(println)

    // TODO 关闭环境
    sc.stop()
  }

}
