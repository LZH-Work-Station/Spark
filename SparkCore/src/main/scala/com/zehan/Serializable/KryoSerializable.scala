package com.zehan.Serializable

import org.apache.spark.{SparkConf, SparkContext}

object KryoSerializable {
  def main(args: Array[String]): Unit = {
    // Kryo 序列化框架 相比于直接Serializable更加轻量级 简单数据类型doukeyiyongkryo来序列化
    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("Serializable").set("spark.serializer",
      "org.apache.spark.serializer.KryoSerializer").registerKryoClasses(Array(classOf[User]))
    val sc = new SparkContext(sparkConf)

    val rdd1 = sc.makeRDD(List(1, 2, 3, 4, 5, 6))


    val user = new User1()

    rdd1.foreach(data => println(data + " " + user.age))

    sc.stop()
  }

}


class User1 extends Serializable {
  val age: Int = 30;
}

