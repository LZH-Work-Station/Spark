package com.zehan.Persist

import org.apache.spark.rdd.RDD
import org.apache.spark.storage.StorageLevel
import org.apache.spark.{SparkConf, SparkContext}

object Test_01_CacheAndPersist {
  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setMaster("local").setAppName("WordCount")
    val sc = new SparkContext(sparkConf)

    val rdd: RDD[String] = sc.makeRDD(List("Hello scala", "Hello Spark"))
    val rddFlatMap: RDD[String] = rdd.flatMap(_.split(" "))
    //----------------- persist和cache被保存的RDD 在程序被执行完毕后会被自动删除-----------------------
    // (1)使用.cache来持久化 RDD到内存   .persist是持久化到硬盘
    // (2)persist会更加安全 但是涉及到磁盘IO, 性能相比于内存会更弱一些
    // (3)cache和persist会在血缘关系中添加新的依赖，一旦出现问题比如数据丢失可以重头读取数据
    // (4)checkPoint 是长久保存到磁盘文件，为了保证数据安全 他会独立进行作业（从makeRDD重新走一遍然后到检查点）
    //    所以checkPoint我们可以和 cache一起用，先cache，然后再checkPoint就不会再重走一遍了。（Test_02里面有例子）
    // (5)checkPoint会切断血缘关系，在执行过程中会重新建立新的血缘，意思是计算的结果 不再和它前面的步骤产生关系，
    //    变成独立的RDD. 因为checkPoint是足够安全，不用担心例如OOM导致的数据丢失
    val rddMap: RDD[(String, Int)] = rddFlatMap.map((_, 1)).cache()
    // StorageLevel有很多选项可以进行选择
    val rddMapInDisk: RDD[(String, Int)] = rddFlatMap.map((_, 1)).persist(StorageLevel.DISK_ONLY)

    val rddCount: RDD[(String, Int)] = rddMap.reduceByKey(_ + _)
    // 由于 RDD中无法对数据进行保存，所以，这一个groupBy会从makeRDD开始重新执行一遍
    // 所以 使用持久化操作 持久化13行代码执行的map的结果（这个缓存的操作会在下面的执行算子那里缓存）
    val rddGrouByKey: RDD[(String, Iterable[Int])] = rddMap.groupByKey()

    rddCount.collect().foreach(println)

    rddGrouByKey.collect().foreach(println)
    sc.stop()
  }
}
