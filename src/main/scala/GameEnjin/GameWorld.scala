package GameEnjin

import GameEnjin.Geometry.{CircleShape, Vector2}
import GameEnjin.Physics.{Physics, PhysicsObject}
import GameEnjin.Rendering.{Color, Drawer, ShapeVisual}
import GameEnjin.Rendering.Swing.SwingDrawer

import scala.swing.Graphics2D

class GameWorld extends App {
  val physics: Physics = new Physics
  val drawer: Drawer = new SwingDrawer
  val physObject = new PhysicsObject
  physObject.velocity += Vector2(10)
  val startingPoint: List[GameObject] = List(new GameObject("Eerste Game Object") {
    position = Vector2(40, 80)
    addComponent(new ShapeVisual(new CircleShape(17), Color.black))
    addComponent(physObject)
  })

  val o = startingPoint.head
  println("o.components: " + o.components)
  println("o.hasComponent[PhysOb]): "+o.hasComponent[PhysicsObject])
  println("o.hasComponent[ShapeVisual]): "+o.hasComponent[ShapeVisual])

  // Local vars:
  var running: Boolean = true
  var lastFrame: Long = 0

  def start(FPS: Int = 60) =
    val frameDuration: Long = math.pow(10, 9).toLong / FPS // in nano-seconds
    while (running)
      if (System.nanoTime() - lastFrame > frameDuration)
        lastFrame = System.nanoTime()

        // Run game:
        physics.step(startingPoint, frameDuration/1000000000)
        drawer.draw(startingPoint)
        //

//        println("frame set duration: " + (frameDuration/1000000) + "ms")
//        println("sleep time: " + ((frameDuration - System.nanoTime() + lastFrame) / 1000000) + "ms")
//        println("frame compute time: " + ((System.nanoTime() - lastFrame) / 1000000) + "ms")
        val sleepTime = (frameDuration - System.nanoTime() + lastFrame) / 1000000 // in milli-seconds
        if (sleepTime > 0) Thread.sleep(sleepTime) else println("Frame took longer than set frameDuration to compute.")
    println("GameEnjin.Game ended")
}