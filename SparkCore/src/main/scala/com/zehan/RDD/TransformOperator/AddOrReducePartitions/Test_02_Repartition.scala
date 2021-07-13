package com.zehan.RDD.TransformOperator.AddOrReducePartitions

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.rdd.RDD

object Test_02_Repartition {
  def main(args: Array[String]): Unit = {
    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("operator")
    val sc = new SparkContext(sparkConf)

    val rdd = sc.makeRDD(List(1, 2, 3, 4), 1)

    // 必须要打乱分区，不然分区不能拆分 不能组合成新的分区
    // val newRdd: RDD[Int] = rdd.coalesce(2, true)
    // 等价于 ： repartition

    val newRdd: RDD[Int] = rdd.repartition(4)
    newRdd.saveAsTextFile("Output")
    sc.stop()
  }
}
