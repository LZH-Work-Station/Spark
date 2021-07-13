package com.zehan.Demo.wc

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.rdd.RDD
import org.json4s.scalap.Main


object Spark03_WordCountReduceByKey {
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
    // 4. reduceByKey,对相同key的做聚合
    val wordToCount: RDD[(String, Int)] = wordTuple.reduceByKey(_ + _)
    // 5. 转化Array并输出
    val wordCount: Array[(String, Int)] = wordToCount.collect()
    println(wordCount.mkString(", "))

    // TODO 关闭连接
    sc.stop()

  }
}
