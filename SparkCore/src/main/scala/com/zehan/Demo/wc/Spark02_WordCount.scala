package com.zehan.Demo.wc

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.rdd.RDD

object Spark02_WordCount {
  def main(args: Array[String]): Unit = {
    // TODO 在POM中加入依赖关系, 建立和Spark框架的连接
    val sparkConf = new SparkConf().setMaster("local").setAppName("WordCount")
    val sc = new SparkContext(sparkConf)

    // TODO 执行业务操作

    // 1. 读取文件, 一行一行的获取数据
    val lines: RDD[String] = sc.textFile("./SparkCore/src/main/resources")
    // 2. 分词：将单词flattenMap(扁平化)
    val words: RDD[String] = lines.flatMap(_.split(" "))
    // 3. (hello) => (hello, 1)
    val wordTuple: RDD[(String, Int)] = words.map(word => (word, 1))
    // 4. 将数据根据单词进行分组，便于统计
    val wordGroup: RDD[(String, Iterable[(String, Int)])] = wordTuple.groupBy(word => word._1)
    // (hello, 1), (hello, 1) => (hello, 2)
//    val wordToCount: RDD[(String, Int)] = wordGroup.mapValues(
//      list => list.map(_._2).sum
//    )
    val wordToCount: RDD[(String, Int)] = wordGroup.map {
      case (word, list) => {
        list.reduce((t1, t2) => {
          (t1._1, t1._2 + t2._2)
        })

      }
    }
    // . 将转换结果采集到控制台打印
    val array: Array[(String, Int)] = wordToCount.collect()
    println(array.mkString(", "))

    // TODO 关闭连接
    sc.stop()
  }
}
