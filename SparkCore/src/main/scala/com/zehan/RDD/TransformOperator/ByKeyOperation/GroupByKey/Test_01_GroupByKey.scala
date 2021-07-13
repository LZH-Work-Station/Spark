package com.zehan.RDD.TransformOperator.ByKeyOperation.GroupByKey

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Test_01_GroupByKey {
  def main(args: Array[String]): Unit = {
    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("operator")
    val sc = new SparkContext(sparkConf)

    val rdd: RDD[(String, Int)] = sc.makeRDD(List(("a", 1), ("a", 1), ("b", 2), ("b", 3), ("a", 4)))

    // 形成元组集合，元组的第一个元素是Key，第二个元素是相同Key的value集合
    val rddGroupByKey: RDD[(String, Iterable[Int])] = rdd.groupByKey()
    rddGroupByKey.foreach(println)
    // =======================groupByKey和reduceByKey的区别========================
    // rdd是分布在很多分区上的话，groupBy会进行一个shuffle操作，把有相同的key的放在同一个分区内
    // 在这个shuffle操作中 会有一个落盘操作（将数据放进磁盘中）防止OOM，然后每个分区再通过IO读取磁盘中分好组的数据

    // 再reduceByKey中，他会在分区内提前做个聚合，例如把某个分区的有相同key的数据先reduce一下，防止io操作过多
    // 即再shuffle之前对分区内数据进行预处理，减少落盘的数据量（效率更高）
    val rddCount: RDD[(String, Int)] = rddGroupByKey.map(
      tuple => {
        (tuple._1, tuple._2.sum)
      }
    )
    rddCount.collect().foreach(println)

    sc.stop()
  }
}
