package com.zehan.RDD.TransformOperator.Map

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.rdd.RDD

object Test03_MapPartitionsWithIndex {
  def main(args: Array[String]): Unit = {
    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("operator")
    val sc = new SparkContext(sparkConf)

    val rdd = sc.makeRDD(List(1, 2, 3, 4), 2)


    // index是数据对应的分区索引
    val mapRDD = rdd.mapPartitionsWithIndex(
      (index, iter) => {
        iter.map(
          num => (index, num)
        )
      }
    )

    println(mapRDD.collect().mkString(", "))
    sc.stop()
  }
}
