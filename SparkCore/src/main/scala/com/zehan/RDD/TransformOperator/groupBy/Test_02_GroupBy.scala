package com.zehan.RDD.TransformOperator.groupBy

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.rdd.RDD

object Test_02_GroupBy {
    def main(args: Array[String]): Unit = {
      val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("operator")
      val sc = new SparkContext(sparkConf)

      //TODO 算子GroupBy

      val rdd = sc.makeRDD(List("Hello", "Spark", "Scala", "Hadoop"), 2)

      // 分组和分区没有必然的关系，一个组的数据在同一个分区，但是同一个分区不一定只有一个分组
      // 分组会让数据 被 shuffle打乱重新组合 即
      val rddGroupBy = rdd.groupBy(_.charAt(0))

      rddGroupBy.collect().foreach(println)
      sc.stop()

    }
  }

