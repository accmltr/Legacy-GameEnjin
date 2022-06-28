package gameEnjin.rendering

import gameEnjin.core.GameObject
import gameEnjin.geometry.{PolygonShape, Vector2}

// Interface for drawing things to be displayed on the next frame.
trait Drawer {
  def draw(gameObjects: List[GameObject]): Unit =
    gameEnjin.utils.forAllGameObjectsAndChildren(gameObjects, o => o.getComponents[VisualData].foreach(_.draw(this)))

  def drawCricle(position: Vector2, radius: Float, color: Color): Unit
  def drawPolygon(position: Vector2, polygon:PolygonShape, color: Color): Unit
}