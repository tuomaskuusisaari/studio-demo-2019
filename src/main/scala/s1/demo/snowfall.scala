package s1.demo

import scala.collection.mutable.Queue
import o1._
import o1.gui._
import scala.util.Random
import scala.collection.mutable.Buffer
import scala.math.exp

object snowfall extends Effect(500,500) {
  
  val kellotaulu = circle(50, White)
  var viisari1 = triangle(3,5, Black).clockwise(90).rightOf(rectangle(20,3, Black).rightOf(triangle(3,25, White).counterclockwise(90)))
  var viisari2 = triangle(6,3, Black).counterclockwise(90).leftOf(rectangle(12,6, Black).leftOf(triangle(6, 15, White).clockwise(90)))
 
  val kuusi      = triangle(30, 60, White).onto(triangle(30, 60, White), TopCenter).onto(triangle(30, 60, White), TopCenter)
  val minikuusi  = triangle(15, 30, White).onto(triangle(15, 30, White), TopCenter).onto(triangle(15, 30, White), TopCenter)
  val house1     = rectangle(50, 10, Red).onto(rectangle(50, 30, Brown), TopCenter)
  val house      = triangle(50, 20, Red).onto(house1, TopCenter)
  val mökki      = rectangle(40, 10, DarkGreen).onto(rectangle(40, 60, Grey), TopCenter)
  val lampi      = rectangle(40, 10, LightBlue)
  
  var counter = 0
  var circleBuffer = Buffer[Pic]()
  var posCount = Buffer[Pos]()
  var timeCounter = 0
  var speedCounts = Buffer[Int]()
  var yCounts = Buffer[Int]()
  var xCounts = Buffer[Int]()
  var askeltaja = 0
  var anotherCounter = 0
  
  while(anotherCounter<200) {
    xCounts+=Random.nextInt(800)
    anotherCounter +=1
  }
  
  def changeThings() = {
   while (counter <200) {
      circleBuffer += circle(Random.nextInt(20), White)
      posCount += new Pos(xCounts(counter), 0) 
      speedCounts += 5 + Random.nextInt(10)
      yCounts += -Random.nextInt(100)
      counter +=1
    }
   
   //changes y coordinate based on its previous coordinate, which will be implemented on the snowflakes coordinate
   for (index <- 0 until posCount.size) {
     if (yCounts(index) < 500) yCounts(index) += speedCounts(index)
     else yCounts(index) = 0
    }
   
   //changes x coordinate, which will be implemented on the snowflakes coordinate 
   
  for (index <- 0 until posCount.size) {
    if(xCounts(index)>=500) xCounts(index) = Random.nextInt(100)+400
    else if(xCounts(index)<=0) xCounts(index) = 500
    else if(xCounts(index)<500 && xCounts(index)>0 && timeCounter<200 && timeCounter>50) xCounts(index) -=5
    else if(xCounts(index)<500 && xCounts(index)>0 && timeCounter<600 && timeCounter>400) xCounts(index) -=5
    
  }
  
  //changes snowflakes x and y coordinate based on xCount and yCount and turns the clock 
   
   for (index <- 0 until posCount.size) {
     posCount(index) = new Pos(xCounts(index), yCounts(index))
   }
   
   viisari1 = viisari1.clockwise(15)
   viisari2 = viisari2.counterclockwise(3)
   
    timeCounter += 1
    }
  
  
  
  def makePic() = {
   val ground     = rectangle(500, 80, White)
   val kello = kellotaulu.place(viisari1, new Pos(25,25)).place(viisari2, new Pos(25,25))
   var background1 = rectangle(500, 500, Black).place(ground, BottomCenter).place(circle(50, White), new Pos(400, 100)).place(circle(50, Black), new Pos(390, 100)).place(kuusi, new Pos(250, 407)).place(kuusi, new Pos(0, 407)).place(kuusi, new Pos(500, 407)).place(minikuusi, new Pos(30, 433)).place(minikuusi, new Pos(220, 433)).place(minikuusi, new Pos(280,433)).place(minikuusi, new Pos(470, 433))
   var background = background1.place(house, new Pos(150, 450)).place(mökki, new Pos(440, 450)).place(lampi, new Pos(320, 470))
   for ( (one, two) <- circleBuffer zip posCount) {
     background = background.place(one, two)
   }
   background = background.place(kello, new Pos(100, 100))
   
   background
   }
 
 
 
  
  def next = {
    if(timeCounter < 600) false 
    else true
  }
  
}