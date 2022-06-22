package GameEnjin.Rendering

import GameEnjin.Geometry.Vector2

// Interface for drawing things to be displayed on the next frame.
trait Drawer {
  def draw: Unit
  def drawCricle(position: Vector2, radius: Float, color: Color): Unit
}
