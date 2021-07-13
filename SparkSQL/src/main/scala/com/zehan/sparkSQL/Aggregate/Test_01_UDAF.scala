package com.zehan.sparkSQL.Aggregate

import org.apache.spark.SparkConf
import org.apache.spark.sql.expressions.{MutableAggregationBuffer, UserDefinedAggregateFunction}
import org.apache.spark.sql.types._
import org.apache.spark.sql.{DataFrame, Row, SparkSession}

object Test_01_UDAF {
  def main(args: Array[String]): Unit = {
    // TODO 创建sparkSql的环境
    val sparkSQLConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("sparkSQL")
    val spark = SparkSession.builder().config(sparkSQLConf).getOrCreate() // 隐式转换规则将age读取到的值从BigInt转换成Long, 必须要import

    // TODO 执行逻辑操作

    // TODO DataFrame 类似于Excel表
    val df: DataFrame = spark.read.json("SparkSQL/src/main/resources/user.json")

    // 我们定义的MyAvgUADF是弱类型的。弱类型的意思就是问题只有在运行的时候才会发现问题
    // aggregate是强类型，在编程的时候就能发现问题
    spark.udf.register("ageAvg", new MyAvgUADF)
    spark.sql("select ageAvg(age) from user").show()

    // TODO 关闭环境
    spark.close()
  }

  /*
  不推荐使用了
    自定义聚合函数: 计算平均值
    1. 继承UserDefinedAggregateFunction
    2. 重写方法
   */
  class MyAvgUADF extends UserDefinedAggregateFunction {
    // 输入数据的结构
    override def inputSchema: StructType = {
      StructType(
        Array(
          StructField("age", LongType)
        )
      )
    }

    // 缓冲区数据的结构
    override def bufferSchema: StructType = {
      StructType(
        Array(
          StructField("total", LongType),
          StructField("count", LongType)
        )
      )
    }

    // 输出的结果类型
    override def dataType: DataType = DoubleType

    // 函数稳定性
    override def deterministic: Boolean = true

    // 缓冲区初始化
    override def initialize(buffer: MutableAggregationBuffer): Unit = {
      // 定义bufferSchema里面的 count和total的初始值
      buffer(0) = 0L
      buffer(1) = 0L
    }

    // 根据输入的值更新缓冲区
    override def update(buffer: MutableAggregationBuffer, input: Row): Unit = {
      buffer(0) = buffer.getLong(0) + input.getLong(0)
      buffer(1) = buffer.getLong(0) + 1
    }

    // 缓冲区数据合并
    override def merge(buffer1: MutableAggregationBuffer, buffer2: Row): Unit = {
      buffer1(0) = buffer1.getLong(0) + buffer2.getLong(0)
      buffer1(1) = buffer1.getLong(1) + buffer2.getLong(1)
    }

    // 计算 最终结果
    override def evaluate(buffer: Row): Double = {
      buffer.getLong(0) / buffer.getLong(1).toDouble
    }
  }
}
