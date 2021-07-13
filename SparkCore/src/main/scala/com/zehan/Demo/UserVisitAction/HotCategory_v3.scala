package com.zehan.Demo.UserVisitAction

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object HotCategory_v3 {
  def main(args: Array[String]): Unit = {
    // TODO 获取上下文和数据
    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("HotCategory")
    val sc = new SparkContext(sparkConf)

    val rdd: RDD[String] = sc.textFile("SparkCore/src/main/resources/user_visit_action.txt")

    // TODO 过滤以对用户行为分类
    val clickAction: RDD[String] = rdd.filter(
      action => {
        val datas: Array[String] = action.split("_")
        datas(6) != "-1"
      }
    )
    val clickRDD: RDD[(String, (Int, Int, Int))] = clickAction.map(
      action => {
        val datas: Array[String] = action.split("_")
        (datas(6), (1, 0, 0))
      }
    )

    val orderAction: RDD[String] = rdd.filter(
      action => {
        val datas: Array[String] = action.split("_")
        datas(8) != "null"
      }
    )
    val orderRDD: RDD[(String, (Int, Int, Int))] = orderAction.flatMap(
      action => {
        val data: Array[String] = action.split("_")
        val datas: Array[String] = data(8).split(",")
        datas.map((_, (0, 1, 0)))
      }
    )

    val payAction: RDD[String] = rdd.filter(
      action => {
        val datas: Array[String] = action.split("_")
        datas(10) != "null"
      }
    )

    val payRDD: RDD[(String, (Int, Int, Int))] = payAction.flatMap(
      action => {
        val data: Array[String] = action.split("_")
        val datas: Array[String] = data(10).split(",")
        datas.map((_, (0, 0, 1)))
      }
    )

    val summaryRDD: RDD[(String, (Int, Int, Int))] = clickRDD.union(orderRDD).union(payRDD)

    val resCount: RDD[(String, (Int, Int, Int))] = summaryRDD.reduceByKey(
      (t1, t2) => {
        (t1._1 + t2._1, t1._2 + t2._2, t1._3 + t2._3)
      }
    )
    val top10Result: Array[(String, (Int, Int, Int))] = resCount.sortBy(_._2, false).take(10)

    top10Result.foreach(println)
  }
}
