package com.zehan.RDD.TransformOperator.ByKeyOperation.ReduceByKey

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Test_01_ReduceByKey {
  def main(args: Array[String]): Unit = {
    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("operator")
    val sc = new SparkContext(sparkConf)

    val rdd: RDD[(String, Int)] = sc.makeRDD(List(("a", 1), ("a", 1), ("b", 2), ("b", 3), ("a", 4)))

    // 对相同Key的Value做聚合操作
    val newRdd: RDD[(String, Int)] = rdd.reduceByKey((x, y) => x + y)
    newRdd.collect().foreach(println)

    sc.stop()
  }
}
