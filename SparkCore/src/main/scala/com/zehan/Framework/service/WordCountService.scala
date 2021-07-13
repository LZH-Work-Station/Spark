package com.zehan.Framework.service

import com.zehan.Framework.common.TService
import com.zehan.Framework.dao.WordCountDao
import org.apache.spark.rdd.RDD

// 服务层 用于实现数据分析操作
class WordCountService extends TService{
  private val wordCountDao = new WordCountDao

  override def dataAnalysis(): Array[(String, Int)] = {
    // 1. 获取数据
    val lines: RDD[String] = wordCountDao.readFile("SparkCore/src/main/resources/1.txt")
    // 2. 分词：将单词flattenMap(扁平化)
    val words: RDD[String] = lines.flatMap(_.split(" "))
    // 3. (hello) => (hello, 1)
    val wordTuple: RDD[(String, Int)] = words.map(word => (word, 1))
    // 4. reduceByKey,对相同key的做聚合
    val wordToCount: RDD[(String, Int)] = wordTuple.reduceByKey(_ + _)
    // 5. 转化Array并输出
    val wordCount: Array[(String, Int)] = wordToCount.collect()
    wordCount
  }
}
