package com.zehan.Persist

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.rdd.RDD
import org.apache.spark.storage.StorageLevel

object Test_02_CheckPoint {
  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setMaster("local").setAppName("WordCount")
    val sc = new SparkContext(sparkConf)

    sc.setCheckpointDir("cp")

    val rdd: RDD[String] = sc.makeRDD(List("Hello scala", "Hello Spark"))
    val rddFlatMap: RDD[String] = rdd.flatMap(_.split(" "))

    val rddMap: RDD[(String, Int)] = rddFlatMap.map((_, 1))
    // 一般会保存在分布式储存系统中： HDFS，我们演示在本地
    // checkPoint是将 RDD的中间结果写入磁盘，不会被删除
    // cache后的checkpoint不再从头运行一遍，而是从缓存区直接拿数据，不用重新执行一遍每一步的RDD，效率更加高
    rddMap.cache()
    rddMap.checkpoint()

    val rddGrouByKey: RDD[(String, Iterable[Int])] = rddMap.groupByKey()

    rddGrouByKey.collect().foreach(println)
    sc.stop()
  }
}
