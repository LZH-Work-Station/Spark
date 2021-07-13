package com.zehan.RDD.TransformOperator.Distinct

import org.apache.spark.{SparkConf, SparkContext}

object Test_01_Distinct {
  def main(args: Array[String]): Unit = {
    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("operator")
    val sc = new SparkContext(sparkConf)

    val rdd = sc.makeRDD(List(1, 1, 2, 3, 3, 4, 5))
    rdd.distinct().collect().foreach(println)


  }
}
