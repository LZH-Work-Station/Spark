package com.zehan.Demo.UserVisitAction

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object HotCategory_v2 {
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
    val clickRDD: RDD[(String, Int)] = clickAction.map(
      action => {
        val datas: Array[String] = action.split("_")
        (datas(6), 1)
      }
    ).reduceByKey(_+_)

    val orderAction: RDD[String] = rdd.filter(
      action => {
        val datas: Array[String] = action.split("_")
        datas(8) != "null"
      }
    )
    // 扁平化
    val orderRDD: RDD[(String, Int)] = orderAction.flatMap(
      action => {
        val data: Array[String] = action.split("_")
        val datas: Array[String] = data(8).split(",")
        datas.map((_, 1))
      }
    ).reduceByKey(_+_)

    val payAction: RDD[String] = rdd.filter(
      action => {
        val datas: Array[String] = action.split("_")
        datas(10) != "null"
      }
    )

    val payRDD: RDD[(String, Int)] = payAction.flatMap(
      action => {
        val data: Array[String] = action.split("_")
        val datas: Array[String] = data(10).split(",")
        datas.map((_, 1))
      }
    ).reduceByKey(_+_)

    // 不用cogroup了
    val clickCount: RDD[(String, (Int, Int, Int))] = clickRDD.map {
      case (item, count) => {
        (item, (count, 0, 0))
      }
    }
    val orderCount: RDD[(String, (Int, Int, Int))] = orderRDD.map {
      case (item, count) => {
        (item, (0, count, 0))
      }
    }
    val payCount: RDD[(String, (Int, Int, Int))] = payRDD.map {
      case (item, count) => {
        (item, (0, 0, count))
      }
    }

    val summaryCount: RDD[(String, (Int, Int, Int))] = clickCount.union(orderCount).union(payCount)
    val resCount: RDD[(String, (Int, Int, Int))] = summaryCount.reduceByKey(
      (t1, t2) => {
        (t1._1 + t2._1, t1._2 + t2._2, t1._3 + t2._3)
      }
    )
    val top10Result: Array[(String, (Int, Int, Int))] = resCount.sortBy(_._2, false).take(10)

    top10Result.foreach(println)
  }
}
