package com.zehan.Demo.SystemDistribue

import java.io.{InputStream, ObjectInputStream}
import java.net.{ServerSocket, Socket}

object Executor {
  def main(args: Array[String]): Unit = {

    // TODO 启动服务器 接收数据
    val server = new ServerSocket(9999)
    println("==============服务器启动，等待客户端连接==============")
    val client: Socket = server.accept()

    val in: InputStream = client.getInputStream
    val objIn = new ObjectInputStream(in)
    val task = objIn.readObject().asInstanceOf[SubTask]
    val ints: List[Int] = task.compute()

    println("接收到数据[9999]: " + ints)

    objIn.close()
    client.close()
    server.close()


  }
}
