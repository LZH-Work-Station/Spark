package com.zehan.sparkSQL.JDBC

import org.apache.spark.SparkConf
import org.apache.spark.sql.{DataFrame, SaveMode, SparkSession}

object Test_01_JDBC {
  def main(args: Array[String]): Unit = {
    // TODO 创建sparkSql的环境
    val sparkSQLConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("sparkSQL")
    val spark = SparkSession.builder().config(sparkSQLConf).getOrCreate()
    import spark.implicits._ // 隐式转换规则将age读取到的值从BigInt转换成Long, 必须要import

    val df: DataFrame = spark.read
      .format("jdbc")
      .option("url", "jdbc:mysql://localhost:3306/login?useSSL=false") // url, login
      .option("driver", "com.mysql.jdbc.Driver")
      .option("user", "root")
      .option("password", "123456")
      .option("dbtable", "user")
      .load()

    df.show()

    // 将df数据 到Mysql中
    df.write
      .format("jdbc")
      .option("url", "jdbc:mysql://localhost:3306/login?useSSL=false") // url, login
      .option("driver", "com.mysql.jdbc.Driver")
      .option("user", "root")
      .option("password", "123456")
      .option("dbtable", "user2")
      .mode(SaveMode.Append)
      .save()

  }

}
