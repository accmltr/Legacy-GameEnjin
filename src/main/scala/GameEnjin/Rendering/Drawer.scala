package GameEnjin.Rendering

import GameEnjin.GameObject
import GameEnjin.Geometry.Vector2

// Interface for drawing things to be displayed on the next frame.
trait Drawer {
  def draw(gameObjects: List[GameObject]): Unit

  def drawCricle(position: Vector2, radius: Float, color: Color): Unit
}
