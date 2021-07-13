package chapiter04_io

import java.io.{File, PrintWriter}
import scala.io.Source

object TestIO {
  def main(args: Array[String]): Unit = {
    // 1、读取文件
    Source.fromFile("src/main/resources/Test.txt").foreach(print)

    // 2. 将数据写入文件
    val writer = new PrintWriter(new File("src/main/resources/output.txt"))
    writer.write("Hello World")
    writer.close()
  }

}
