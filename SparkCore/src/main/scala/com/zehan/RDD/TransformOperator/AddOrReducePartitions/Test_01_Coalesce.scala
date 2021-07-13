package com.zehan.RDD.TransformOperator.AddOrReducePartitions

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Test_01_Coalesce {
  def main(args: Array[String]): Unit = {
      val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("operator")
      val sc = new SparkContext(sparkConf)

      val rdd = sc.makeRDD(List(1, 2, 3, 4), 4)

      // 合并分区，本来应该是一个数字一个分区，经过coalesce后会将 （1，2）放进一个分区，（3，4）放进一个分区
      // 他不会拆分分区, 比如原来3个分区 要合并成两个 就会变成 有一个新分区有2个之前的分区，另一个新分区有1个之前的
      // 分区，就会造成数据倾斜

      // 如果下面的coalesce 的shuffle = true的话，就可以打乱和拆开分区，实现数据均衡
      val newRdd: RDD[Int] = rdd.coalesce(2, true)

      newRdd.saveAsTextFile("Output")
      sc.stop()
  }
}
