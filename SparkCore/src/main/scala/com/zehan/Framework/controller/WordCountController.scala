package com.zehan.Framework.controller

import com.zehan.Framework.common.TController
import com.zehan.Framework.service.WordCountService

// 控制层 用于调度
class WordCountController extends TController{
  private val wordCountService = new WordCountService
  // 调度
  override def dispatch(): Unit = {
    val array: Array[(String, Int)] = wordCountService.dataAnalysis()
    array.foreach(println)
  }
}
