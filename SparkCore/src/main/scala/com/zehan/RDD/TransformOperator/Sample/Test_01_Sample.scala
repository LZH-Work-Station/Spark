package com.zehan.RDD.TransformOperator.Sample

import org.apache.spark.{SparkConf, SparkContext}

object Test_01_Sample {
  def main(args: Array[String]): Unit = {
    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("operator")
    val sc = new SparkContext(sparkConf)

    val rdd = sc.makeRDD(List(1, 2, 3, 4, 5, 6, 7, 8, 9, 0))

    println(rdd.sample(
      false, // 是否放回
      0.4, // 每个元素被抽取的概率
      6 // 随机的种子， 如果不传递第三个参数 就seed就随机改变 改变的值和系统时间有关
    ).collect().mkString(", "))
  }
}
