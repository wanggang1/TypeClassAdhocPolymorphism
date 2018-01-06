package org.gwgs

object worksheet {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  
  //import Readable._
  //import StringOps._
  import Helpers._
  
  5 times println("Hi")                           //> Hi
                                                  //| Hi
                                                  //| Hi
                                                  //| Hi
                                                  //| Hi
  "20".read[Double]                               //> res0: Double = 20.0
  "20".read[Int]                                  //> res1: Int = 20
}