package com.zehan.Partition

import org.apache.spark.{Partitioner, SparkConf, SparkContext}
import org.apache.spark.rdd.RDD

object Test_01_Partition {
  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setMaster("local").setAppName("WordCount")
    val sc = new SparkContext(sparkConf)

    val rdd: RDD[(String, String)] = sc.makeRDD(List(
      ("nba", "xxxxxxxxxxxxxxxxx"),
      ("nba", "xxxxxxxxxxxxxxxxx"),
      ("cba", "xxxxxxxxxxxxxxxxx"),
      ("wnba", "xxxxxxxxxxxxxxxxx")
    ), 3)

    val rddPartition: RDD[(String, String)] = rdd.partitionBy(new MyPartitioner)

    rddPartition.saveAsTextFile("OutPut")
    sc.stop()
  }

  /**
   * 自定义分区器
   * 1. 继承Partitioner
   * 2. 重写方法
   */
  class MyPartitioner extends Partitioner{
    // 分区数量
    override def numPartitions: Int = 3
    // 返回数据的分区索引
    override def getPartition(key: Any): Int = {
      key match {
        case "nba" => 0
        case "wnba" => 1
        case _ => 2
      }
    }
  }

}


