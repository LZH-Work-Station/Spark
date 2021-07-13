package com.zehan.Demo.SystemDistribue

class SubTask extends Serializable {
  var datas: List[Int] = _
  var logic: Int => Int = _

  // 计算
  def compute(): List[Int] ={
    datas.map(logic)
  }

}
