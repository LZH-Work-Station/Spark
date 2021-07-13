package chapiter07_流程控制

import scala.util.control.Breaks
import scala.util.control.Breaks._

object TestInterruption {
  def main(args: Array[String]): Unit = {
    // (1) 抛出异常来中断循环
    try {
      for (elem <- 1 to 10) {
        println(elem)
        if (elem == 5) throw new RuntimeException
      }
    } catch {
      case e : Exception =>
    }
    println("正常结束循环")

    // (2) Scala的breakable函数, 本质还是实现一个try catch 抛出异常，异常的return类型是nothing
    Breaks.breakable(
      for(i <- 1 until 10){
        if(i==6) Breaks.break()
        println(i)
      }
    )

    // (3) 简化(2)的内容， 直接import scala.util.control.Breaks._
    breakable(
      for(i <- 1 until 10){
        if(i==6) break()
        println(i)
      }
    )
  }
}
