package com.zehan.RDD.TransformOperator.groupBy

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

import java.text.SimpleDateFormat
import java.util.Date

object Test_03_GroupByDate {
  def main(args: Array[String]): Unit = {
    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("operator")
    val sc = new SparkContext(sparkConf)

    val date: RDD[String] = sc.textFile("SparkCore/src/main/resources/date.txt")
    val dateGroupBy: RDD[(String, Iterable[(String, Int)])] = date.map(
      line => {
        val sdf = new SimpleDateFormat("dd/MM/yyyy:HH:mm:ss")
        val date: Date = sdf.parse(line)
        val sdf1 = new SimpleDateFormat("yyyy")
        (sdf1.format(date), 1)
      }
    ).groupBy(date => date._1)

    val dateCount: RDD[(String, Int)] = dateGroupBy.map {
      case (date, iter) => {
        iter.reduce(
          (t1, t2) => (t1._1 , t1._2 + t2._2)
        )
      }

    }
    dateCount.foreach(println)
  }
}
