package com.zehan.RDD.ActionOperator.Collect

import org.apache.spark.{SparkConf, SparkContext}

object Test_01_Collect {
  def main(args: Array[String]): Unit = {
      val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("operator")
      val sc = new SparkContext(sparkConf)

      val rdd1 = sc.makeRDD(List(1, 2, 3, 4, 5))

      // 将数据按照分区顺序采集到内存中形成数组
      val res: Array[Int] = rdd1.collect()

      println(res.mkString(", "))

      sc.stop()
  }
}
