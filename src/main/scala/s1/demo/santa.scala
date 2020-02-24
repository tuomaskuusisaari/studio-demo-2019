package s1.demo

import scala.collection.mutable.Queue
import o1._
import scala.util.Random
import scala.collection.mutable.Buffer
import scala.math.exp
import javax.imageio.ImageIO
import java.io.File
import s1.image.ImageExtensions._
import scala.math._


object santa extends Effect (500, 500) {
  
  val background = rectangle(500, 500, Black) 
  val kuusi      = triangle(30, 60, White).onto(triangle(30, 60, White), TopCenter).onto(triangle(30, 60, White), TopCenter)
  val minikuusi  = triangle(15, 30, White).onto(triangle(15, 30, White), TopCenter).onto(triangle(15, 30, White), TopCenter)
  val ground     = rectangle(500, 80, White)
  val house1     = rectangle(50, 10, Red).onto(rectangle(50, 30, Brown), TopCenter)
  val house      = triangle(50, 20, Red).onto(house1, TopCenter)
  val mökki      = rectangle(40, 10, DarkGreen).onto(rectangle(40, 60, Grey), TopCenter)
  val lampi      = rectangle(40, 10, LightBlue)
  val combined1  = background.place(ground, BottomCenter).place(kuusi, new Pos(250, 407)).place(kuusi, new Pos(0, 407)).place(kuusi, new Pos(500, 407)).place(minikuusi, new Pos(30, 433)).place(minikuusi, new Pos(220, 433)).place(minikuusi, new Pos(280,433)).place(minikuusi, new Pos(470, 433))
  var combined   = combined1.place(house, new Pos(150, 450)).place(mökki, new Pos(440, 450)).place(lampi, new Pos(320, 470))
  var counter    = 0
  var y          = 0.0
  var currentPos = new Pos(0, 60)
  
  val santa1     = Pic("yhdistetty.png").scaleBy(0.35)
  
  def yPos = {
    y += Pi/10
    sin(Pi/6*y)*40+100
  }
  
  
  def changeThings = {
    
    this.combined = this.combined.shiftLeft(2.0)
    
    this.currentPos = new Pos(this.currentPos.x + 3, this.yPos)
    
    this.counter += 1
    
  }
  
  def makePic() = {
    var kuva = combined.place(santa1, this.currentPos)
    kuva
  }
  
  def next = {
    if (this.counter < 300) {
      false
    } else {
      true
    }
  }
  
}