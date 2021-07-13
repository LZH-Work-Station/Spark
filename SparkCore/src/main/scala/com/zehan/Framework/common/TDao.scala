package com.zehan.Framework.common

import com.zehan.Framework.util.EnvUtil
import org.apache.spark.SparkContext
import org.apache.spark.rdd.RDD

trait TDao {
  def readFile(path: String): RDD[String] ={
    // 1. 读取文件, 一行一行的获取数据
    val sc: SparkContext = EnvUtil.take()
    sc.textFile(path)
  }
}
