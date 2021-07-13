package chapiter09_面向对象.Test_06_Trait

object TestSelfType {
  def main(args: Array[String]): Unit = {
    val user: RegisteUserInfo = new RegisteUserInfo("Li Zehan", "Jia Jia")
    user.insert()
  }
}

class User(var username: String, var password: String)

trait UserDao{
  _: User => // 当我们不想使用继承 但是想在特质中使用到别的类中定义的属性，可以用这种方法
  def insert(): Unit = {
    println("insert into DB " + this.username + " " + this.password)
  }
}

class RegisteUserInfo(username: String, password: String) extends User(username, password) with UserDao{
  override def insert(): Unit = super.insert()
}
