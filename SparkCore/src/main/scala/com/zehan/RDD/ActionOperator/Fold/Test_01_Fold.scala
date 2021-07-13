package com.zehan.RDD.ActionOperator.Fold

import org.apache.spark.{SparkConf, SparkContext}

object Test_01_Fold {
  def main(args: Array[String]): Unit = {
    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("operator")
    val sc = new SparkContext(sparkConf)

    val rdd1 = sc.makeRDD(List(1, 2, 3, 4), 2)

    // (初始值， 分区内和分区间规则)
    // fold和foldByKey的区别也是初始值会参与分区间运算
    val res: Int = rdd1.fold(10)(_ + _)

    println(res)

    sc.stop()
  }
}
