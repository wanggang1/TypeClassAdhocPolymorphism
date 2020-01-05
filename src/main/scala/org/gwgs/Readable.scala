package org.gwgs

trait Readable[T] {
  
  def read(x: String): T
  
}


/**
 * Companion object containing helper functions and standard implementations
 */
object Readable {

  /**
    * SAM (Single Abstract Method) - Helper function which allows creation of Readable instances
    */
  def toReadable[T](p: String => T): Readable[T] = new Readable[T] {
    def read(x: String): T = p(x)
  }

  /**
    * Instead of implicit conversion class...
    *   implicit class ReadableString(val s: String) extends AnyVal {
    *     def readT[T: Readable]: T = implicitly[Readable[T]].read(s)
    *   }
    *
    *   val x: Double = "10.1".readT[Double]
    *   val y: Int = "10".readT[Int]
    *
    * apply allows:
    *   Readable[Double].read("10.1")
    *   Readable[Int].read("10")
    */
  def apply[A](implicit instance: Readable[A]): Readable[A] = instance

  /*
  Using the toReadable creates cleaner code, we could also explicitly
  define the implicit instances:

  implicit object ReadableDouble extends Readable[Double] {
    def read(s: String): Double = s.toDouble
  }
  implicit object ReadableInt extends Readable[Int] {
    def read(s: String): Int = s.toInt
  }
  */
  implicit val ReadableInt = toReadable[Int](_.toInt)
  implicit val ReadableLong = toReadable[Long](_.toLong)
  implicit val ReadableDouble = toReadable[Double](_.toDouble)
  implicit val ReadableString = toReadable[String](new String(_))
  implicit val ReadableBoolean = toReadable[Boolean](_.toBoolean)
  implicit val ReadableCharList = toReadable[List[Char]](_.toCharArray.toList)
  implicit val ReadableStringList = toReadable[List[String]](_.split(':').toList)

  // simple convert the incoming string to a Task
  implicit val readableTask = toReadable(
    _.split('|') match {
      case Array(id: String, title: String, content: String) => new Task(Readable[Long].read(id), title, content)
    }
  )

}

case class Task(id: Long, title: String, content: String)

/**
  * Extend the string object with a read function
  *
  * Or to any types...
  *   implicit class ShowOps[A](val a: A) extends AnyVal {
  *     def show(implicit sh: Show[A]) = sh.show(a)
  *   }
  */
object StringOps {

  implicit class ReadableString(val s: String) extends AnyVal  {

    /**
      * This is a shorthand syntax for implicit parameters, called Context Bounds.
      *
      * The type parameter should have an implcit Readable in scope. Use
      * implicitly to access it and call the read function
      */
    def readT[T: Readable]: T = implicitly[Readable[T]].read(s)
  }

}