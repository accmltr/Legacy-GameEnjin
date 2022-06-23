package GameEnjin

import GameEnjin.Geometry.{CircleShape, Vector2}
import GameEnjin.Physics.{Physics, PhysicsObject}
import GameEnjin.Rendering.{Color, Drawer, ShapeVisual}
import GameEnjin.Rendering.Swing.SwingDrawer

import scala.swing.Graphics2D

class GameWorld() extends App {
  val physics: Physics = new Physics
  val drawer: Drawer = new SwingDrawer

  // Local vars:
  var running: Boolean = true
  var lastFrame: Long = 0

  def start(startingPoint: List[GameObject], FPS: Int = 60) =
    val frameDuration: Long = math.pow(10, 9).toLong / FPS.toLong // in nano-seconds
    while (running)
      if (System.nanoTime() - lastFrame > frameDuration)
        lastFrame = System.nanoTime()

        // Run game:
        physics.step(startingPoint, frameDuration/ 1000000000.0f)
        drawer.draw(startingPoint)
        //

//        println("frame set duration: " + (frameDuration/1000000.0) + "ms")
//        println("sleep time: " + ((frameDuration - System.nanoTime() + lastFrame) / 1000000.0) + "ms")
//        println("frame compute time: " + ((System.nanoTime() - lastFrame) / 1000000.0) + "ms")
        val sleepTime = (frameDuration - System.nanoTime() + lastFrame) / 1000000 // in milli-seconds
        if (sleepTime > 0) Thread.sleep(sleepTime) else println("Frame took longer than set frameDuration to compute.")
    println("GameEnjin.Game ended")
}