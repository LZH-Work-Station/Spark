package com.zehan.sparkSQL.Demo

import com.zehan.sparkSQL.Aggregate.Test_01_UDAF_Aggregator.MyAvgUDAF
import io.netty.buffer.Unpooled.buffer
import org.apache.spark.SparkConf
import org.apache.spark.sql.expressions.Aggregator
import org.apache.spark.sql.{DataFrame, Encoder, Encoders, SparkSession, functions}

import scala.collection.mutable
import scala.collection.mutable.ListBuffer

object Test_01_Demo {
  def main(args: Array[String]): Unit = {
    // TODO 创建sparkSql的环境
    val sparkSQLConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("sparkSQL")
    val spark = SparkSession.builder().config(sparkSQLConf).getOrCreate()
    import spark.implicits._ // 隐式转换规则将age读取到的值从BigInt转换成Long, 必须要import


    val product_info: DataFrame = spark.read
      .format("jdbc")
      .option("url", "jdbc:mysql://localhost:3306/product?useSSL=false") // url, login
      .option("driver", "com.mysql.jdbc.Driver")
      .option("user", "root")
      .option("password", "123456")
      .option("dbtable", "product_info")
      .load()
    product_info.createOrReplaceTempView("product_info")

    val user_visit_action: DataFrame = spark.read
      .format("jdbc")
      .option("url", "jdbc:mysql://localhost:3306/product?useSSL=false") // url, login
      .option("driver", "com.mysql.jdbc.Driver")
      .option("user", "root")
      .option("password", "123456")
      .option("dbtable", "user_visit_action")
      .load()
    user_visit_action.createOrReplaceTempView("user_visit_action")

    val city_info: DataFrame = spark.read
      .format("jdbc")
      .option("url", "jdbc:mysql://localhost:3306/product?useSSL=false") // url, login
      .option("driver", "com.mysql.jdbc.Driver")
      .option("user", "root")
      .option("password", "123456")
      .option("dbtable", "city_info")
      .load()
    city_info.createOrReplaceTempView("city_info")


    spark.udf.register("cityRemarkUDAF", functions.udaf(new CityRemarkUDAF))

    spark.sql(
      """
        |select *
        |from (select *, rank() over(partition by area order by `点击次数` desc) as rank
        |      from(
        |          select c.area, product_name, count(*) as `点击次数`, cityRemarkUDAF(city_name)
        |          from user_visit_action a, product_info p, city_info c
        |          where a.click_product_id = p.product_id and a.city_id = c.city_id and
        |            a.click_product_id > -1
        |          group by c.area, product_name
        |      )t
        |)t1
        |where `rank` <= 3
        |""".stripMargin
    ).show(false)


  }
  /*
  自定义聚合函数：实现城市备注
  1。 继承Aggregater，定义泛型
  IN: 传入 城市名称
  BUFF: Map[城市，点击数量]
  OUT: 输出 备注信息
   */
  case class Buffer(var total: Long, var cityMap: mutable.Map[String, Long])

  class CityRemarkUDAF extends Aggregator[String, Buffer, String]{
    override def zero: Buffer = Buffer(0, mutable.Map[String, Long]())

    override def reduce(b: Buffer, a: String): Buffer = {
      b.total += 1
      val newCount = b.cityMap.getOrElse(a, 0L) + 1
      b.cityMap.update(a, newCount)
      b
    }

    override def merge(b1: Buffer, b2: Buffer): Buffer = {
      b1.total += b2.total
      val map1 = b1.cityMap
      val map2 = b2.cityMap

      // 两个map的合并操作
      map2.foreach {
        case (city, count) => {
          val newCount = map1.getOrElse(city, 0L) + count
          map1.put(city, newCount)
        }
      }
      b1.cityMap = map1
      b1
    }

    override def finish(reduction: Buffer): String = {
      val remarkList = ListBuffer[String]()

      val totalcnt = reduction.total
      val cityMap = reduction.cityMap

      val citySort: List[(String, Long)] = cityMap.toList.sortWith(
        (left, right) => {
          left._2 > right._2
        }
      ).take(2)

      val hasMore = cityMap.size > 2
      var rsum = 0L
      citySort.foreach {
        case (city, cnt) => {
          val r = cnt * 100 / totalcnt
          remarkList.append(s"${city} ${r}%")
          rsum += r
        }
      }

      if( hasMore ) {
        remarkList.append(s"其他 ${100 - rsum}%")
      }

      remarkList.mkString(", ")
    }

    override def bufferEncoder: Encoder[Buffer] = Encoders.product

    override def outputEncoder: Encoder[String] = Encoders.STRING
  }
}
