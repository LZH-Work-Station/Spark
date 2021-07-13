package com.zehan.Framework.util

import org.apache.spark.SparkContext

object EnvUtil {
  private val scLocal = new ThreadLocal[SparkContext]()

  def put(sc : SparkContext): Unit ={
    scLocal.set(sc)
  }

  def take() = {
    scLocal.get()
  }

  def clear() = {
    scLocal.remove()
  }
}
