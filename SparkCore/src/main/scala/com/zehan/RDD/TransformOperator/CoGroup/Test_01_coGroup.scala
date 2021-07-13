package com.zehan.RDD.TransformOperator.CoGroup

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.rdd.RDD

object Test_01_coGroup {
  def main(args: Array[String]): Unit = {
    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("operator")
    val sc = new SparkContext(sparkConf)

    val rdd1: RDD[(String, Int)] = sc.makeRDD(List(("a", 1), ("b", 2), ("c", 3), ("d", 13)))
    val rdd2: RDD[(String, Int)] = sc.makeRDD(List(("a", 4), ("b", 6), ("c", 9), ("a", 2)))

    // coGroup = connect + group 先将RDD中有相同key的做connect再把不同的RDD进行group放在一
    // 本质是分组连接
    val rddCoGroup: RDD[(String, (Iterable[Int], Iterable[Int]))] = rdd1.cogroup(rdd2)

    rddCoGroup.collect().foreach(println)
    sc.stop()
  }
}
