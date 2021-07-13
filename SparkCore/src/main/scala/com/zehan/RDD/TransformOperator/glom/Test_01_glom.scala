package com.zehan.RDD.TransformOperator.glom

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.rdd.RDD

object Test_01_glom {
  def main(args: Array[String]): Unit = {
    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("operator")
    val sc = new SparkContext(sparkConf)

    val rdd = sc.makeRDD(List(1, 2, 3, 4), 2)


    // 将同一分区内的数据放在一起
    val rddGlom: RDD[Array[Int]] = rdd.glom()

    // 案例 分区最大值求和
    val rddMAX: RDD[Int] = rddGlom.map(
      data => {
        data.max
      }
    )
    //println(rddGlom.collect().foreach(data => println(data.max)))
    println(rddMAX.collect().sum)
    sc.stop()
  }
}
