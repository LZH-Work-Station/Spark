package com.zehan.RDD.ActionOperator.Aggregate

import org.apache.spark.{SparkConf, SparkContext}

object Test_01_Aggregate {
  def main(args: Array[String]): Unit = {
    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("operator")
    val sc = new SparkContext(sparkConf)

    val rdd1 = sc.makeRDD(List(1, 2, 3, 4), 2)

    // (初始值， 分区内规则， 分区间规则)
    // aggregateByKey的初始值只参与分区内计算
    // aggregate的初始值不仅参与分区内计算 还参与分区间计算
    val res: Int = rdd1.aggregate(0)(_ + _, _ + _)

    println(res)

    sc.stop()
  }
}
