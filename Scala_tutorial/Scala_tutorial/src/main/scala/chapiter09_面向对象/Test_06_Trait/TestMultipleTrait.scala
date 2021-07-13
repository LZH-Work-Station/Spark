package chapiter09_面向对象.Test_06_Trait

object TestMultipleTrait {
  def main(args: Array[String]): Unit = {
    val myball: MyBall = new MyBall
    println(myball.describe())
  }


}
/*
  在这里面的结构是color category都继承Ball, 当我们的实现类 实现color和category的时候，首先
  会考虑最右面的特征，如果然后super.describe调用color的describe，然后再调用Category的describe， 由于
  category和color中的super.describe都是Ball中的方法，他就会只出现一次即category中的super.describe方法
  失效，被叠加在后面。所以这个describe的顺序是color-》category-》Ball.顺序是从最右往最左
 */
trait Ball{
  def describe(): String = "Ball"
}

trait Color extends Ball{
  var color: String = "yellow"
  override def describe(): String =  color + super.describe()
}

trait Category extends Ball{
  var category: String = "foot"
  override def describe(): String =  category + super.describe()
}

class MyBall extends Category with Color {
  //  override def describe(): String = super.describe()
  override def describe(): String = super[Category].describe() //如果想只 调用某个父类的接口的话
}