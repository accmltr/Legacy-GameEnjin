package Rendering

import Geometry.Vector2

// Interface for drawing things to be displayed on the next frame.
trait Drawer {
  def drawCricle(position: Vector2, radius: Float, color: Color): Unit
}
