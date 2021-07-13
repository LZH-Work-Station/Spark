package com.zehan.sparkSQL.Aggregate

import org.apache.spark.SparkConf
import org.apache.spark.sql.expressions.Aggregator
import org.apache.spark.sql._

object Test_01_UDAF_Aggregator {
  def main(args: Array[String]): Unit = {
    // TODO 创建sparkSql的环境
    val sparkSQLConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("sparkSQL")
    val spark = SparkSession.builder().config(sparkSQLConf).getOrCreate() // 隐式转换规则将age读取到的值从BigInt转换成Long, 必须要import

    // TODO 执行逻辑操作

    // TODO DataFrame 类似于Excel表
    val df: DataFrame = spark.read.json("SparkSQL/src/main/resources/user.json")

    df.createOrReplaceTempView("user")

    spark.udf.register("ageAvg", functions.udaf(new MyAvgUDAF))

    spark.sql("select ageAvg(age) from user").show()

    // TODO 关闭环境
    spark.close()

  }

  /*
    自定义聚合函数类:计算年龄的平均值
      继承Aggregate
      IN : 输入的类型
      BUF : 缓冲区的数据类型
      OUT : 输出的类型
   */
  case class Buff(var total: Long, var count: Long)

  class MyAvgUDAF extends Aggregator[Long, Buff, Long] {
    // 缓冲区初始值
    override def zero: Buff = {
      Buff(0L, 0L)
    }

    // 更新缓冲区
    override def reduce(b: Buff, a: Long): Buff = {
      b.total += a
      b.count += 1
      b
    }

    // 合并缓冲区
    override def merge(b1: Buff, b2: Buff): Buff = {
      b1.total += b2.total
      b1.count += b2.count
      b1
    }

    // 计算结果
    override def finish(reduction: Buff): Long = {
      reduction.total / reduction.count
    }

    // 缓冲区的编码操作(product是自己创建的类，scalaLong这种scala里面有的基本类型就对应基本类型)
    override def bufferEncoder: Encoder[Buff] = {
      Encoders.product
    }

    // 输出的编码操作
    override def outputEncoder: Encoder[Long] = {
      Encoders.scalaLong
    }
  }
}
