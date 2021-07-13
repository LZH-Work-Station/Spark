package com.zehan.RDD.ActionOperator.First

import org.apache.spark.{SparkConf, SparkContext}

object Test_01_First {
  def main(args: Array[String]): Unit = {
      val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("operator")
      val sc = new SparkContext(sparkConf)

      val rdd1 = sc.makeRDD(List(1, 2, 3, 4, 5))

      // 取第一个数据
      val res: Int = rdd1.first()

      println(res)

      sc.stop()
  }
}
