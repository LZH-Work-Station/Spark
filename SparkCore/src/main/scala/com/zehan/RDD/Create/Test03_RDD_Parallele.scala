package com.zehan.RDD.Create

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Test03_RDD_Parallele {
  def main(args: Array[String]): Unit = {
    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("RDD")
    ////可以更改默认的核数
    // sparkConf.set("spark.default.parallelism", "5")
    val sc = new SparkContext(sparkConf)

    // TODO 创建RDD
    // 后面这个参数代表有多少个分区，即多少个并行进程
    // 如果不传这个参数 会使用默认的并行度, 默认是适用所有核数
    val rdd: RDD[Int] = sc.makeRDD(List(1, 2, 3, 4, 5), 2)

    // TODO 将处理的数据保存成分区文件
    rdd.saveAsTextFile("Output")

    // TODO 关闭环境
    sc.stop()
  }

}
