package com.zehan.IO

import org.apache.spark.{SparkConf, SparkContext}

object Test_01_IO {
  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("WordCount")
    val sc = new SparkContext(sparkConf)

    val rdd = sc.makeRDD(List(("a", 1), ("b", 2), ("c", 3), ("d", 4), ("e", 5)))

    rdd.saveAsTextFile("output1")
    rdd.saveAsObjectFile("output2")
    // 要求SequenceFile 必须是键值对才行
    rdd.saveAsSequenceFile("output3")

    sc.textFile("output1").collect().foreach(println)
    sc.objectFile("output2").collect().foreach(println)
    sc.sequenceFile[String, Int]("output3").collect().foreach(println)

    sc.stop()
  }
}
