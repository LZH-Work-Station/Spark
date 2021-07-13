package com.zehan.Framework.common

import com.zehan.Framework.util.EnvUtil
import org.apache.spark.{SparkConf, SparkContext}

trait TApplication {
  def start(master: String = "local[*]", appName: String = "WordCount")(op: => Unit) = {
    val sparkConf = new SparkConf().setMaster(master).setAppName(appName)
    val sc = new SparkContext(sparkConf)
    EnvUtil.put(sc)

    try{
      op
    }catch {
      case ex => println(ex.getMessage)
    }
    sc.stop()
    EnvUtil.clear()
  }
}
