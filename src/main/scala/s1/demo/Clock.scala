package s1.demo

import scala.collection.mutable.Queue
import o1._

import scala.util.Random
import scala.collection.mutable.Buffer
import scala.math.exp


object Clock extends Effect(500,500) {
  
  val kellotaulu = circle(50, White)
  var viisari1 = triangle(3,5, Black).clockwise(90).rightOf(rectangle(20,3, Black).rightOf(triangle(3,25, White).counterclockwise(90)))
  var viisari2 = triangle(6,3, Black).counterclockwise(90).leftOf(rectangle(12,6, Black).leftOf(triangle(6, 15, White).clockwise(90)))

      
  var timeCounter = 0
  
  def changeThings() = {
    viisari1 = viisari1.clockwise(15)
    viisari2 = viisari2.counterclockwise(3)
    
    timeCounter += 1
  }
  
  def makePic() = {
   var background = rectangle(500, 500, Black).place(circle(50, White), new Pos(400, 100)).place(circle(50, Black), new Pos(390, 100))
   val kello = kellotaulu.place(viisari1, new Pos(25,25)).place(viisari2, new Pos(25,25))
   
   background = background.place(kello, new Pos(400, 400))
   
   background
  }
  
  def next = {
    if( timeCounter < 1000 ) false 
    else true
  }
  
}