package com.zehan.Accumulator

import org.apache.spark.rdd.RDD
import org.apache.spark.util.AccumulatorV2
import org.apache.spark.{SparkConf, SparkContext}

import scala.collection.mutable

object Test_02_MyAccumulator {
  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("WordCount")
    val sc = new SparkContext(sparkConf)

    val rdd: RDD[String] = sc.makeRDD(List("hello", "spark", "hello"))

    // 自定义累加器
    //（1） 创建累加器对象
    //（2） 向Spark注册
    val wcAcc = new MyAccumulator
    sc.register(wcAcc, "wordCountAcc")

    rdd.foreach(
      word => {
        // 数据的累加
        wcAcc.add(word)
      }
    )
    println(wcAcc.value)

    sc.stop()
  }
  /*
    自定义数据累加器： WordCount
    1. 继承AccumulatorV2定义泛型
        IN : 累加器输入的数据类型
        OUT : 累加器返回的数据类型mutable.Map[String, long]
   */
  class MyAccumulator extends AccumulatorV2[String, mutable.Map[String, Long]]{
    private var wcMap = mutable.Map[String, Long]()
    // 判断是否为初始状态
    override def isZero: Boolean = wcMap.isEmpty

    override def copy(): AccumulatorV2[String, mutable.Map[String, Long]] = new MyAccumulator

    override def reset(): Unit = wcMap.clear

    // 获取累加器需要计算的值
    override def add(word: String): Unit = {
      val newCount: Long = wcMap.getOrElse(word, 0L) + 1
      wcMap.update(word, newCount)
    }
    // 聚合两个分区的数据
    override def merge(other: AccumulatorV2[String, mutable.Map[String, Long]]): Unit = {
      val map1: mutable.Map[String, Long] = other.value
      val map2: mutable.Map[String, Long] = this.wcMap

      map1.foreach{
        case(word, count) => {
          val newCount: Long = map2.getOrElse(word, 0L) + count
          map2.update(word, newCount)
        }
      }
    }
    // 返回值
    override def value: mutable.Map[String, Long] = {
      wcMap
    }
  }
}
