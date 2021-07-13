package com.zehan.Demo.wc

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Spark01_WordCount {
  def main(args: Array[String]): Unit = {
    // TODO 在POM中加入依赖关系, 建立和Spark框架的连接
    val sparkConf = new SparkConf().setMaster("local").setAppName("WordCount")
    val sc = new SparkContext(sparkConf)

    // TODO 执行业务操作

    // 1. 读取文件, 一行一行的获取数据
    val lines: RDD[String] = sc.textFile("./SparkCore/src/main/resources")
    // 2. 分词：将单词flattenMap(扁平化)
    val words: RDD[String] = lines.flatMap(_.split(" "))
    // 3. 将数据根据单词进行分组，便于统计
    val wordGroup: RDD[(String, Iterable[String])] = words.groupBy(word => word)
    // 4. 对分组后的数据进行转换
    val wordToCount: RDD[(String, Int)] = wordGroup.mapValues(list => list.size)
    // 5. 将转换结果采集到控制台打印
    val array: Array[(String, Int)] = wordToCount.collect()
    println(array.mkString(", "))

    // TODO 关闭连接
    sc.stop()

  }
}
