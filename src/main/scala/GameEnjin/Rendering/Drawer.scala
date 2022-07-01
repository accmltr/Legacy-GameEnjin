package gameEnjin.rendering

import gameEnjin.core.{GameObject, GameWorld}
import gameEnjin.geometry.{PolygonShape, Vector2}

// Interface for drawing things to be displayed on the next frame.
trait Drawer(val world:GameWorld) {
  def draw(gameObjects: List[GameObject]): Unit =
    gameEnjin.utils.forAllGameObjectsAndChildren(
      gameObjects,
      o => {
        o.getComponents[VisualData].foreach(_.draw(this))
        true
      }
      ,
      world
    )

  def drawCricle(position: Vector2, radius: Float, color: Color): Unit
  def drawPolygon(position: Vector2, polygon:PolygonShape, color: Color): Unit
}