package GameEnjin

import GameEnjin.Physics.Physics
import GameEnjin.Rendering.Drawer
import GameEnjin.Rendering.Swing.SwingDrawer

import scala.swing.Graphics2D

class GameWorld extends App {
  val physics: Physics = new Physics
  val drawer: Drawer = new SwingDrawer
  val startingPoint: List[GameObject] = List(new GameObject) 
  
  // Local vars:
  var running: Boolean = true
  var lastFrame: Long = 0

  def startLoop(FPS: Int = 60) =
    val frameDuration: Long = math.pow(10, 9).asInstanceOf[Long] / FPS
    while (running)
      if (System.nanoTime() - lastFrame > frameDuration)
        lastFrame = System.nanoTime()
        
        // Run game:
        drawer.draw
        //
        
        Thread.sleep((frameDuration - System.nanoTime() + lastFrame) / 1000000)
    println("GameEnjin.Game ended")
}