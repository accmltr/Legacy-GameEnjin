package gameEnjin.core

import gameEnjin.geometry.{CircleShape, Vector2}
import gameEnjin.physics.{Physics, Rigidbody}
import gameEnjin.rendering.swing.SwingDrawer
import gameEnjin.rendering.{Color, Drawer, ShapeVisuals}

import scala.swing.Graphics2D

class GameWorld() extends App {

  val physics: Physics = new Physics
  val drawer: Drawer = new SwingDrawer

  private var FPS: Int = 60
  private var frameDuration: Long = (1000000000 / FPS.toDouble).toLong // The desired frame duration, in nano-seconds.
  private var running: Boolean = true

  def stop = running = false
  def start(scene: List[GameObject]) =
    loop(scene)
  private def loop(scene: List[GameObject], lastStartTime: Long = System.nanoTime()): Unit =

    // Stop game:
    if (!running) return

    val frameStartTime = System.nanoTime()

    // Run game:
    physics.step(scene, (frameStartTime - lastStartTime) / 1000000000.0f)
    drawer.draw(scene)
    //

    // Sleep until next frame needs to be started:
    val sleepTime = (frameDuration - System.nanoTime() + frameStartTime) / 1000000 // in milli-seconds
    if (sleepTime > 0) Thread.sleep(sleepTime) //else println("Frame took longer than set desiredFrameDuration to compute.")

    // Start next frame:
    loop(scene, frameStartTime)
  def setFPS(fps: Int) =
    FPS = fps
    frameDuration = (1000000000 / FPS.toDouble).toLong

}