package com.zehan.RDD.ActionOperator.Take

import org.apache.spark.{SparkConf, SparkContext}

object Test_01_Take {
  def main(args: Array[String]): Unit = {
      val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("operator")
      val sc = new SparkContext(sparkConf)

      val rdd1 = sc.makeRDD(List(1, 2, 3, 4, 5))

      // 数据的数量
      val res: Array[Int] = rdd1.take(3)

      println(res.mkString(", "))

      sc.stop()
  }
}
