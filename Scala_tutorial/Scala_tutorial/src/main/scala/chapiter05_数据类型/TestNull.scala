package chapiter05_数据类型

import chapiter01.Student

object TestNull {

  def methode1(): Unit = {
    println("m1被调用")
  }
  def methode2(n: Int): Int = {
    if(n==0){
      throw new Exception("n = 0了")
    }else{
      return n
    }
  }

  def main(args: Array[String]): Unit = {
    // (1) Unit 作用和 void 一样，只有一个实例就是空括号()，Unit的伴生类定义了一个box方法，返回一个BoxedUnit对象，
    // 里面重写了toString方法，toString的内容是"()"
    println(methode1())
    // (2) Null 唯一实例 null
    var alice: Student = null
    println(alice)
    // (3) 所有scala类型的子类 根本没有正常的返回值，例如会抛出异常
    var b = methode2(0)
    println(b)
  }
}
