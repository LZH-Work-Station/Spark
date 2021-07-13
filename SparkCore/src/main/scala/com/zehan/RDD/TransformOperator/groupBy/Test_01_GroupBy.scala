package com.zehan.RDD.TransformOperator.groupBy

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Test_01_GroupBy {
  def main(args: Array[String]): Unit = {
    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("operator")
    val sc = new SparkContext(sparkConf)

    //TODO 算子GroupBy

    val rdd: RDD[Int] = sc.makeRDD(List(1, 2, 3, 4, 5, 6), 2)

    // groupBy将数据源中每一个数据进行分组判断，根据返还的key分组，相同的会放在一个分组中
    val rddGroupBy: RDD[(Int, Iterable[Int])] = rdd.groupBy(num => num%2)

    rddGroupBy.collect().foreach(
      tuple => {
        println(tuple._2.mkString(", "))
      }
    )
    sc.stop()

  }
}
