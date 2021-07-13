package com.zehan.Demo.SystemDistribue

class Task extends Serializable {

  val datas = List(1, 2, 3, 4)

  // 逻辑运算
  val logic: Int => Int = (_: Int) * 2

  // 计算
  def compute(): List[Int] ={
    datas.map(logic)
  }
}
