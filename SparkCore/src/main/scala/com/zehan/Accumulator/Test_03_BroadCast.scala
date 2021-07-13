package com.zehan.Accumulator

import org.apache.spark.broadcast.Broadcast
import org.apache.spark.rdd.RDD
import org.apache.spark.util.LongAccumulator
import org.apache.spark.{SparkConf, SparkContext}

import scala.collection.mutable

object Test_03_BroadCast {
  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("WordCount")
    val sc = new SparkContext(sparkConf)

    val rdd1 = sc.makeRDD(List(("a", 1), ("b", 2), ("c", 3), ("d", 4)))
    val rdd2 = sc.makeRDD(List(("a", 4), ("b", 3), ("c", 1), ("d", 2)))

    // join会导致数据量的几何增长，影响shuffle的性能 join的效果（"a", (1, 4)）
    val joinRDD: RDD[(String, (Int, Int))] = rdd1.join(rdd2)
    joinRDD.collect().foreach(println)

    // 所以我们通过广播变量（分布式的共享的只读变量） 创建共享数据（所有的exector共享同一个），但是这个数据是不可更改的
    val map: mutable.Map[String, Int] = mutable.Map(("a", 4), ("b", 3), ("c", 1), ("d", 2))
    val bcMap: Broadcast[mutable.Map[String, Int]] = sc.broadcast(map) //将这个map广播给每个executor

    val rddMap: RDD[(String, (Int, Int))] = rdd1.map {
      case (word, count) => {
        // 通过value访问广播变量
        val newCount = bcMap.value.getOrElse(word, 0)
        (word, (count, newCount))
      }
    }

    rddMap.collect().foreach(println)

    sc.stop()
  }
}
