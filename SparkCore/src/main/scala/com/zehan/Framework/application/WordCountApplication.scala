package com.zehan.Framework.application

import com.zehan.Framework.common.TApplication
import com.zehan.Framework.controller.WordCountController

object WordCountApplication extends App with TApplication{
  // 架构是 application <=>  controller <=> service <=> Dao(持久层) <=> file
  // 继承了Trait中的方法, Trait中定义了我们的环境，将我们的操作 传入到start中
  start(){
    val controller = new WordCountController
    controller.dispatch()
  }
}
