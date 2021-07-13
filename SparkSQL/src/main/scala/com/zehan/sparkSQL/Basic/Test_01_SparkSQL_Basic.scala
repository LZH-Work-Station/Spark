package com.zehan.sparkSQL.Basic

import org.apache.spark.SparkConf
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{DataFrame, Dataset, Row, SparkSession}

object Test_01_SparkSQL_Basic {
  def main(args: Array[String]): Unit = {
    // TODO 创建sparkSql的环境
    val sparkSQLConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("sparkSQL")
    val spark = SparkSession.builder().config(sparkSQLConf).getOrCreate()
    import spark.implicits._ // 隐式转换规则将age读取到的值从BigInt转换成Long, 必须要import

    // TODO 执行逻辑操作

    // TODO DataFrame 类似于Excel表
    val df: DataFrame = spark.read.json("SparkSQL/src/main/resources/user.json")

    // DataFrame => SQL
    df.createOrReplaceTempView("user") // 创建视图, 名字叫user
    spark.sql("select * from user").show()

    // DataFrame => DSL
    // 在使用DataFrame时, 如果涉及到转换操作, 需要引入转换规则

    df.select($"age" + 1L).show
    df.select('age + 1L).show // 单引号也可以
    df.select("username", "age").show()

    // TODO DataSet DataSet具有泛型,可以创建数据的样例类,构建样例类的dataSet
    val seq = Seq(1, 2, 3, 4)
    val ds: Dataset[Int] = seq.toDS()
    ds.show()

    // TODO RDD <=> DataFrame
    val rdd: RDD[(Int, String, Int)] = spark.sparkContext.makeRDD(List((1, "zhangsan", 30), (2, "lisi", 40)))
    val df1: DataFrame = rdd.toDF("id", "name", "age")
    val rowRDD: RDD[Row] = df1.rdd

    // TODO DataFrame <=> DataSet
    val ds1: Dataset[User] = df1.as[User]
    val df2: DataFrame = ds1.toDF()

    // TODO RDD <=> DataSet
    val ds2: Dataset[User] = rdd.map {
      case (id, name, age) => {
        User(id, name, age)
      }
    }.toDS()
    val userRDD: RDD[User] = ds2.rdd


    // TODO 关闭环境
    spark.close()
  }

  case class User(id: Int, name: String, age: Int)
}
