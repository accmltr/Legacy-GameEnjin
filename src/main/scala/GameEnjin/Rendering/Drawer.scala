package gameEnjin.rendering

import gameEnjin.core.GameObject
import gameEnjin.geometry.{PolygonShape, Vector2}

// Interface for drawing things to be displayed on the next frame.
trait Drawer {
  def draw(gameObjects: List[GameObject]): Unit =
    _drawGameObjects(gameObjects)
    def _drawGameObjects(l: List[GameObject]): Unit =
      l.foreach { o =>
        o.getComponents[VisualData].foreach(_.draw(this))
        _drawGameObjects(o.children)
      }

  def drawCricle(position: Vector2, radius: Float, color: Color): Unit
  def drawPolygon(position: Vector2, polygon:PolygonShape, color: Color): Unit
}