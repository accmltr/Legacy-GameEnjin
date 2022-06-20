package GameEnjin.Rendering.Swing

import GameEnjin.Geometry.Vector2
import GameEnjin.Rendering.{Color, Drawer}

import scala.swing.Graphics2D

class SwingDrawer(graphics2D: Graphics2D) extends Drawer {
  override def drawCricle(position: Vector2, radius: Float, color: Color): Unit =
    println("Draw a %s circle at %s of radius: %f".format(color, position, radius))
}
