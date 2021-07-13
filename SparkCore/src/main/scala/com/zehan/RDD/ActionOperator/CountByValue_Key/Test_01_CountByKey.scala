package com.zehan.RDD.ActionOperator.CountByValue_Key

import org.apache.spark.{SparkConf, SparkContext}

object Test_01_CountByKey {
  def main(args: Array[String]): Unit = {
      val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("operator")
      val sc = new SparkContext(sparkConf)

      val rdd1 = sc.makeRDD(List(("a", 1), ("a", 2), ("a", 3)))

      // 统计每种Key出现的数量
      val res: collection.Map[String, Long] = rdd1.countByKey()

      println(res)

      sc.stop()
  }
}
