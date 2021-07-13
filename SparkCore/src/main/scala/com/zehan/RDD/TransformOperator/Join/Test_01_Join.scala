package com.zehan.RDD.TransformOperator.Join

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.rdd.RDD

object Test_01_Join {
  def main(args: Array[String]): Unit = {
    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("operator")
    val sc = new SparkContext(sparkConf)

    val rdd1: RDD[(String, Int)] = sc.makeRDD(List(("a", 1), ("b", 2), ("c", 3), ("d", 13)))
    val rdd2: RDD[(String, Int)] = sc.makeRDD(List(("a", 4), ("b", 6), ("c", 9), ("a", 2)))

    // 相同的key不同的数据源的value连接在一起，形成元组
    // 如果有多个匹配，就会形成新的元组 例如上面这个例子会有(a,(1,4))和(a,(1,2))两种
    val joinRdd: RDD[(String, (Int, Int))] = rdd1.join(rdd2)

    // LeftOuterJoin就是Sql那种差不多，哪边是重点哪边就不能为None，不是重点的数据源可以为None
    // RightOuterJoin也是一样的
    val rddLeftOuterJoin: RDD[(String, (Int, Option[Int]))] = rdd1.leftOuterJoin(rdd2)

    joinRdd.collect().foreach(println)
    rddLeftOuterJoin.collect().foreach(println)
    sc.stop()
  }
}
