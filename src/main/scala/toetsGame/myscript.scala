package toetsGame

import gameEnjin.core.{GameObject, GameObjectComponent}
import gameEnjin.geometry.{CircleShape, PolygonShape, Vector2}
import gameEnjin.rendering.{Color, ShapeVisuals}

class myscript extends GameObjectComponent{
  var has = false
  override def update(delta: Float): Unit =
    if (!has)
      new GameObject("Race Car Window") {
        local_position = Vector2(3,0)
        addComponent(new ShapeVisuals(CircleShape(2.5), Color(1, 0, 0)))
        addComponent(new ShapeVisuals(CircleShape(1.5), Color(0.7, .7, 1)))
      }.parent = gameObject
    gameObject.position += Vector2(0,5*delta)
}
