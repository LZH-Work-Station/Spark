package com.zehan.sparkSQL.Aggregate

import org.apache.spark.SparkConf
import org.apache.spark.sql.{DataFrame, SparkSession}

object Test_01_UDF {
  def main(args: Array[String]): Unit = {
    // TODO 创建sparkSql的环境
    val sparkSQLConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("sparkSQL")
    val spark = SparkSession.builder().config(sparkSQLConf).getOrCreate() // 隐式转换规则将age读取到的值从BigInt转换成Long, 必须要import

    // TODO 执行逻辑操作

    // TODO DataFrame 类似于Excel表
    val df: DataFrame = spark.read.json("SparkSQL/src/main/resources/user.json")

    // DataFrame => SQL
    df.createOrReplaceTempView("user") // 创建视图, 名字叫user

    // 通过udf给sql结果增加一些内容
    spark.udf.register("prefixName", (name: String) => "Name: " + name)
    spark.sql("select age, prefixName(username) from user").show()




    // TODO 关闭环境
    spark.close()
  }

  case class User(id: Int, name: String, age: Int)
}
