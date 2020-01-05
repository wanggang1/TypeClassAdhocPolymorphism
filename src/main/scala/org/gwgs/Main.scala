package org.gwgs

object Main {

  def main(args: Array[String]) {
    import StringOps._
    
    //use the apply on companion object
    println(Readable[Double].read("10"))
    println(Readable[Int].read("10"))
    println(Readable[String].read("Well duh!"))
    println(Readable[List[Char]].read("Well duh!"))
    println(Readable[List[String]].read("Using:A:Separator:to:split:a:String"))
    println(Readable[Task].read("10|Title Text|Title Content"))
    
    // we can also use the read function directly
    
    println("20".readT[Double]);
    println("Using:A:Separator:to:split:a:String".readT[List[Char]]);
    println("Using:A:Separator:to:split:a:String".readT[List[String]]);
    println(Readable[Task].read("10|Title Text|Title Content"))
    println("20|Another title Text|Another title Content".readT[Task])
    
  }

}
