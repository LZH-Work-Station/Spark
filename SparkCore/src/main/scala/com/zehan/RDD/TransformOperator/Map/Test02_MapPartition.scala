package com.zehan.RDD.TransformOperator.Map

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Test02_MapPartition {
  def main(args: Array[String]): Unit = {
    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("operator")
    val sc = new SparkContext(sparkConf)

    val rdd = sc.makeRDD(List(1, 2, 3, 4), 2)


    // 将分区数据全部拿到才操作, 类似于BufferedInputStream, 不会存在反复的IO，再内存中运行，效率更高
    // 当数据过大就会造成MMO
    val mapRDD: RDD[Int] = rdd.mapPartitions(
      iter => {
        List(iter.max).iterator
      }
    )

    mapRDD.collect().foreach(println)

    sc.stop()
  }
}
