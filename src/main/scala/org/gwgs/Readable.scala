package org.gwgs

trait Readable[T] {
  
  def read(x: String): T
  
}


/**
 * Companion object containing helper functions and standard implementations
 */
object Readable {

  /**
   * Helper function which allows creation of Readable instances
   */
  def toReadable[T](p: String => T): Readable[T] = new Readable[T] {
    def read(x: String): T = p(x)
  }

  /**
   * Allow for construction of standalone readables, if the ops aren't used
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
  implicit val ReadableDouble2 = new Readable[Double] {
    def read(x: String): Double = x.toDouble
  }
  implicit val ReadableInt = toReadable[Int](_.toInt)
  implicit val ReadableLong = toReadable[Long](_.toLong)
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
   * Extend the string object with a read function.
   */
object StringOps {

  implicit class ReadableString(s: String) {

    /**
     * This is a shorthand syntax for implicit parameters, called Context Bounds.
     * 
     * The type parameter should have an implcit Readable in scope. Use
     * implicitly to access it and call the read function
     */
   def read[T: Readable]= implicitly[Readable[T]].read(s)
  }
  
}