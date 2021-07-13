package com.zehan.RDD.Create

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Test01_RDD_Memory {
  def main(args: Array[String]): Unit = {
    // RDD就是分布式的集合
    // 1. RDD的数据只有在调用collect方法时才会执行业务逻辑，有点像IO一样有不同的修饰方法 不断地修饰数据处理的方法
    // 2. RDD里面只保存处理数据的逻辑并不会保存数据
    // 3. RDD的算子不断封装，在同一分区内 数据是一个一个处理的，不是比如说所有数据都处理map完 才进行下一步的操作，而是
    //    某个数据执行map, reduceByKey, split, flat执行完之后再执行下一个数据
    // TODO 准备环境
    // [*]代表 使用最大的核数并行运行
    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("RDD")
    val sc = new SparkContext(sparkConf)
    // TODO 创建RDD
    // 从内存中创建RDD，数据来源来意内存中的集合
    val seq = Seq[Int](1, 2, 3, 4)
    // parallelize：并行，将集合变成分布式集合 分布在不同的核上，并行度取决于同时使用多少个核
    val rdd: RDD[Int] = sc.parallelize(seq)
    // makeRDD：和parallelize一样
    val rdd2: RDD[Int] = sc.makeRDD(seq)

    rdd.collect().foreach(println)
    rdd2.collect().foreach(println)
    // TODO 关闭环境
    sc.stop()
  }
}
