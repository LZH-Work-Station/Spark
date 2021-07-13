package com.zehan.RDD.ActionOperator.Count

import org.apache.spark.{SparkConf, SparkContext}

object Test_01_Count {
  def main(args: Array[String]): Unit = {
      val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("operator")
      val sc = new SparkContext(sparkConf)

      val rdd1 = sc.makeRDD(List(1, 2, 3, 4, 5))

      // 数据的数量
      val res: Long = rdd1.count()

      println(res)

      sc.stop()
  }
}
