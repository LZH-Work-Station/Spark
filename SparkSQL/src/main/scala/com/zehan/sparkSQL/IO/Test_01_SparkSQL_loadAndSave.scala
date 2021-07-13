package com.zehan.sparkSQL.IO

import org.apache.spark.SparkConf
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{DataFrame, Dataset, Row, SparkSession}

object Test_01_SparkSQL_loadAndSave {
  def main(args: Array[String]): Unit = {
    // TODO 创建sparkSql的环境
    val sparkSQLConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("sparkSQL")
    val spark = SparkSession.builder().config(sparkSQLConf).getOrCreate()
    import spark.implicits._ // 隐式转换规则将age读取到的值从BigInt转换成Long, 必须要import

    /*
      数据的加载
      val df: DataFrame = spark.read.load => .parquet文件
      val df: DataFrame = spark.read.json => .json文件
      // seq: 分隔符, inferSchema: 自动推断数据类型, header: 是否有表头
      val df: DataFrame = spark.read.format("csv").option("seq", ";")
              .option("inferSchema","true").option("header","true").load("data/user.csv")

      ...

      数据的保存
      df.write.save(path路径) ：默认保存的parquet文件
      df.write.format("json").save(path路径)：保存成json文件
      df.write.format("json").mode("append").save(path)  mode: append, overwrite, ignore(有就忽略没有就创建)
    */




    // TODO 关闭环境
    spark.close()
  }

  case class User(id: Int, name: String, age: Int)
}
