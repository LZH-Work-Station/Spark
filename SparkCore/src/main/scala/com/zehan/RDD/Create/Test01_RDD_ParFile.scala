package com.zehan.RDD.Create

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Test01_RDD_ParFile {
  def main(args: Array[String]): Unit = {
    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("RDD")
    val sc = new SparkContext(sparkConf)
    // 默认的最小分区数是2个，可以改变，这里变成3个。分区数量是用总的字节数除以最小分区数得到每个分区放
    // 多少个字节，如果有余数且余数大于每个分区的字节数的百分之10 就会创建一个新的分区
    // 所以有可能是4个分区一共
    val rdd: RDD[String] = sc.textFile("SparkCore/src/main/resources", 3)

    rdd.saveAsTextFile("Output")


  }
}
