package com.zehan.RDD.ActionOperator.CountByValue_Key

import org.apache.spark.{SparkConf, SparkContext}

object Test_01_CountByValue {
  def main(args: Array[String]): Unit = {
      val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("operator")
      val sc = new SparkContext(sparkConf)

      val rdd1 = sc.makeRDD(List(1, 2, 3, 4, 5))

      // 统计每种数据出现的数量
      val res: collection.Map[Int, Long] = rdd1.countByValue()

      println(res)

      sc.stop()
  }
}
