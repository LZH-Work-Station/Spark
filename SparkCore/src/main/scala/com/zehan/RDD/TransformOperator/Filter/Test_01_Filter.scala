package com.zehan.RDD.TransformOperator.Filter

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

import java.text.SimpleDateFormat

object Test_01_Filter {
  def main(args: Array[String]): Unit = {
    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("operator")
    val sc = new SparkContext(sparkConf)

    val date: RDD[String] = sc.textFile("SparkCore/src/main/resources/date.txt")

    // 满足条件的留下 不满足条件的删除
    date.filter(
      line => {
        // 返回一个boolean
        line.startsWith("17/05/2011")
      }
    ).collect().foreach(println)
  }
}