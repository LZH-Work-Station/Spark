package com.zehan.sparkSQL.Hive

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession

object Test_01_Hive {
  def main(args: Array[String]): Unit = {
    // 如果出现权限问题 可以把后面的root换成有权限的username，或者root应该也行
    System.setProperty("HADOOP_USER_NAME", "hive")

    // TODO 创建sparkSql的环境
    val sparkSQLConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("sparkSQL")
    val spark = SparkSession.builder().enableHiveSupport().config(sparkSQLConf).getOrCreate()
    import spark.implicits._ // 隐式转换规则将age读取到的值从BigInt转换成Long, 必须要import

    /*
      使用sparkSQL连接外置的Hive
      1. 拷贝Hive-site.xml文件到classPath下
      2. 启动Hive的支持  val spark = SparkSession.builder().enableHiveSupport().config(sparkSQLConf).getOrCreate()
      3. 增加对应的依赖关系 （包含Mysql驱动）
     */

    // 准备数据
    spark.sql("use tablename") // 选择在hive中的哪个数据库
    spark.sql(
      """
        |CREATE TABLE `product_info`(
        | `product_id` bigint,
        | `product_name` string,
        | `extend_info` string)
        |row format delimited fields terminated by '\t'
        |""".stripMargin
    )
    spark.sql(
      """
        |load data local inpath 'datas/product_info.txt' into tablename.product_info
        |""".stripMargin
    )
    spark.close()
  }
}
