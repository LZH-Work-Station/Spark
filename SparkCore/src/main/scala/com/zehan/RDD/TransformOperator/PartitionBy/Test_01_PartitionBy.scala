package com.zehan.RDD.TransformOperator.PartitionBy

import org.apache.spark.rdd.RDD
import org.apache.spark.{HashPartitioner, SparkConf, SparkContext}

object Test_01_PartitionBy {
  def main(args: Array[String]): Unit = {
    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("operator")
    val sc = new SparkContext(sparkConf)
    //TODO (Key, Value)类型
    val rdd: RDD[Int] = sc.makeRDD(List(1, 2, 3, 4), 2)
    // RDD里面有伴生对象，他会把K, V类型的RDD转化成PairRDD类型，这样才能使用partitionBy方法
    // 普通的RDD不能使用partitionBy方法
    val rddMap: RDD[(Int, Int)] = rdd.map((_, 1))

    // HashPartitioner是按照key的哈希值分区
    val rddHashPartition: RDD[(Int, Int)] = rddMap.partitionBy(new HashPartitioner(2))
    rddHashPartition.saveAsTextFile("Output")



    sc.stop()
  }
}
