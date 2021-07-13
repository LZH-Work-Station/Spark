package com.zehan.RDD.ActionOperator.TakeOrdered

import org.apache.spark.{SparkConf, SparkContext}

object Test_01_TakeOrdered {
  def main(args: Array[String]): Unit = {
      val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("operator")
      val sc = new SparkContext(sparkConf)

      val rdd1 = sc.makeRDD(List(5, 2, 1, 3, 6))

      // 数据的数量
      val res: Array[Int] = rdd1.takeOrdered(3)(Ordering.Int.reverse)

      println(res.mkString(", "))

      sc.stop()
  }
}
