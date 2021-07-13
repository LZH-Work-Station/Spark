package com.zehan.Demo.SystemDistribue

import java.io.{ObjectOutput, ObjectOutputStream, OutputStream}
import java.net.Socket

object Driver {
  def main(args: Array[String]): Unit = {
    // TODO 连接服务器
    val client = new Socket("localhost", 9999)
    val client2 = new Socket("localhost", 8888)
    // TODO 获得总任务
    val task = new Task()
    // TODO 获得输出流，发送任务
    val out1: OutputStream = client.getOutputStream
    val objOut1 = new ObjectOutputStream(out1)
    val subTask1 = new SubTask()
    subTask1.logic = task.logic
    subTask1.datas = task.datas.take(2)
    objOut1.writeObject(subTask1)
    objOut1.flush()

    val out2: OutputStream = client2.getOutputStream
    val objOut2 = new ObjectOutputStream(out2)
    val subTask2 = new SubTask()
    subTask2.logic = task.logic
    subTask2.datas = task.datas.takeRight(2)
    objOut2.writeObject(subTask2)
    objOut2.flush()

    // 关闭输出流 和 过滤流
    out2.close()
    out1.close()
    objOut1.close()
    objOut2.close()
    // 关闭socket
    client.close()
    client2.close()
  }
}
