package com.zehan.RDD.ActionOperator.Reduce

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Test_01_Reduce {
  def main(args: Array[String]): Unit = {
    def main(args: Array[String]): Unit = {
      val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("operator")
      val sc = new SparkContext(sparkConf)

      val rdd1 = sc.makeRDD(List(1, 2, 3, 4, 5))

      val res: Int = rdd1.reduce(_ + _)

      println(res)

      sc.stop()
    }
  }
}
