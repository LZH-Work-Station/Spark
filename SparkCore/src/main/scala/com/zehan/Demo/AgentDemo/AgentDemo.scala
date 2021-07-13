package com.zehan.Demo.AgentDemo

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object AgentDemo {
  def main(args: Array[String]): Unit = {
    // 按照 省份对广告 A B C D做排行，取前两名点击的广告
    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("Agent")
    val sc = new SparkContext(sparkConf)

    val lines: RDD[String] = sc.textFile("logs/agent.log")

    //(河北, A)
    val provinceAndAdvertissment: RDD[(String, String)] = lines.map(
      line => {
        val datas: Array[String] = line.split(" ")
        (datas(1), datas(4))
      }
    )
    // ((河北， A)， 1)
    val rddNewMap: RDD[((String, String), Int)] = provinceAndAdvertissment.map {
      tuple => {
        ((tuple._1, tuple._2), 1)
      }
    }
    // ((河北， A)， 5)
    val rddCount: RDD[((String, String), Int)] = rddNewMap.reduceByKey(_ + _)

    // (河北， (A， 5))
    val rddNewCount: RDD[(String, (String, Int))] = rddCount.map(tuple => (tuple._1._1, (tuple._1._2, tuple._2)))

    // groupBy
    val rddGroupBy: RDD[(String, Iterable[(String, Int)])] = rddNewCount.groupByKey()

    // sortBy
    val rddSort: RDD[(String, List[(String, Int)])] = rddGroupBy.mapValues(
      iter => {
        // 调用的List集合中的sortBy 所以不能使用(_._2, false)来做降序排列
        iter.toList.sortBy(_._2)(Ordering.Int.reverse).take(3)
      }
    )

    // 输出
    rddSort.collect().foreach(println)
  }
}
