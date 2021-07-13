package com.zehan.Demo.UserVisitAction

import org.apache.spark.rdd.RDD
import org.apache.spark.util.AccumulatorV2
import org.apache.spark.{SparkConf, SparkContext}

import scala.collection.mutable

object HotCategory_final_Accumulator {
  def main(args: Array[String]): Unit = {
    // TODO 获取上下文和数据
    // 使用 累加器 不用reduceByKey就不会存在shuffle 效率更高
    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("HotCategory")
    val sc = new SparkContext(sparkConf)

    val rdd: RDD[String] = sc.textFile("SparkCore/src/main/resources/user_visit_action.txt")

    val acc = new MyAccumulator
    sc.register(acc, "hotCategory")

    rdd.foreach(
      (action: String) => {
        val datas: Array[String] = action.split("_")
        if(datas(6)!="-1"){
          acc.add((datas(6), "click"))
        }else if(datas(8)!="null"){
          val ids: Array[String] = datas(8).split(",")
          ids.foreach(
            (id: String) => acc.add((id, "order"))
          )
        }else if(datas(10)!="null"){
          val ids: Array[String] = datas(10).split(",")
          ids.foreach(
            (id: String) => acc.add((id, "pay"))
          )
        }else{
          Nil
        }
      }
    )

    val accCount: mutable.Map[String, HotCategory] = acc.value
    val objectResult: mutable.Iterable[HotCategory] = accCount.map(_._2)
    val resCount: List[(String, (Int, Int, Int))] = objectResult.toList.map(
      category => {
        (category.cid, (category.clickCnt, category.orderCnt, category.payCnt))
      }
    )
    resCount.sortBy(_._2).reverse.take(10).foreach(println)
    sc.stop()

  }

  case class HotCategory(cid: String, var clickCnt: Int, var orderCnt: Int, var payCnt: Int)
  /*
    自定义累加器
    1. 继承AccumulatorV2, 定义泛型
      IN: （品类ID，行为类型）
      OUT: 类加器的本质
   */

  class MyAccumulator extends AccumulatorV2[(String, String), mutable.Map[String, HotCategory]] {
    val map: mutable.Map[String, HotCategory] = mutable.Map()

    override def isZero: Boolean = map.isEmpty

    override def copy(): AccumulatorV2[(String, String), mutable.Map[String, HotCategory]] = new MyAccumulator

    override def reset(): Unit = map.clear()

    override def add(v: (String, String)): Unit = {
      val cid: String = v._1
      val actionType: String = v._2
      val category: HotCategory = map.getOrElse(cid, HotCategory(cid, 0, 0, 0))
      actionType match {
        case "click" => category.clickCnt += 1
        case "order" => category.orderCnt += 1
        case "pay" => category.payCnt += 1
      }
      map.update(cid, category)
    }

    override def merge(other: AccumulatorV2[(String, String), mutable.Map[String, HotCategory]]): Unit = {
      val map1 = this.map
      val map2 = other.value

      map2.foreach{
        case(cid, categoryInMap2) => {
          val categoryInMap1: HotCategory = map1.getOrElse(cid, HotCategory(cid, 0, 0, 0))
          categoryInMap1.payCnt += categoryInMap2.payCnt
          categoryInMap1.orderCnt += categoryInMap2.orderCnt
          categoryInMap1.clickCnt += categoryInMap2.clickCnt
          map1.put(cid, categoryInMap1)
        }
      }
    }

    override def value: mutable.Map[String, HotCategory] = this.map
  }
}
