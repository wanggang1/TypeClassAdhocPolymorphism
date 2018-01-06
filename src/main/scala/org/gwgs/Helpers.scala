package org.gwgs

object Helpers {
  
  implicit class IntWithTimes(x: Int) {
    def times[A](f: => A): Unit = {
      def loop(current: Int): Unit =
        if(current > 0) {
          f
          loop(current - 1)
        }
      loop(x)
    }
  }
  
  implicit class ReadableString(s: String) {

    /**
     * The type parameter should have an implcit Readable in scope. Use
     * implicitly to access it and call the read function
     */
    def read[T: Readable] = implicitly[Readable[T]].read(s)
  }
  
}