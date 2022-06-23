package GameEnjin

import GameEnjin.Geometry.{CircleShape, Vector2}
import GameEnjin.Physics.Physics
import GameEnjin.Rendering.{Color, Drawer, ShapeVisual}
import GameEnjin.Rendering.Swing.SwingDrawer

import scala.swing.Graphics2D

class GameWorld extends App {
  val physics: Physics = new Physics
  val drawer: Drawer = new SwingDrawer
  val startingPoint: List[GameObject] = List(new GameObject("Eerste Game Object") {
    position = Vector2(40, 80)
    addComponent(new ShapeVisual(new CircleShape(17), Color.black))
  })

  // Local vars:
  var running: Boolean = true
  var lastFrame: Long = 0

  def start(FPS: Int = 60) =
    val frameDuration: Long = math.pow(10, 9).asInstanceOf[Long] / FPS
    while (running)
      if (System.nanoTime() - lastFrame > frameDuration)
        lastFrame = System.nanoTime()

        // Run game:
        physics.step(startingPoint, frameDuration)
        drawer.draw(startingPoint)
        //

        Thread.sleep((frameDuration - System.nanoTime() + lastFrame) / 1000000)
    println("GameEnjin.Game ended")
}