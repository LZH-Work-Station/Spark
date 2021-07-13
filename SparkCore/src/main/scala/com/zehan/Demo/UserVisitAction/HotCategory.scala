package com.zehan.Demo.UserVisitAction

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object HotCategory {
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

    val resRDD: RDD[(String, (Iterable[Int], Iterable[Int], Iterable[Int]))] = clickRDD.cogroup(orderRDD, payRDD)
    val resCount: RDD[(String, (Int, Int, Int))] = resRDD.map {
      case (id, (it1, it2, it3)) => {
        var clicktime = 0
        var ordertime = 0
        var paytime = 0
        it1.foreach(count => clicktime = count)
        it2.foreach(count => ordertime = count)
        it3.foreach(count => paytime = count)
        (id, (clicktime, ordertime, paytime))
      }
    }

    val top10Result: Array[(String, (Int, Int, Int))] = resCount.sortBy(_._2, false).take(10)

    top10Result.foreach(println)
  }
}
