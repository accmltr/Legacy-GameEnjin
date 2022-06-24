package gameEnjin.rendering

import gameEnjin.core.GameObject
import gameEnjin.geometry.Vector2

// Interface for drawing things to be displayed on the next frame.
trait Drawer {
  def draw(gameObjects: List[GameObject]): Unit

  def drawCricle(position: Vector2, radius: Float, color: Color): Unit
  def drawPolygon(position: Vector2, points: List[Vector2], color: Color): Unit
}