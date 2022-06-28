package toetsGame

import gameEnjin.core.{GameObject, GameObjectComponent}
import gameEnjin.geometry.{CircleShape, PolygonShape, Vector2}
import gameEnjin.rendering.{Color, CircleVisualData}

class myscript extends GameObjectComponent{
  var has = false
  override def update(delta: Float): Unit =
    if (!has) {
      // Do _ready() code:
//      println(gameObject.name + " is ready!")
      val o = new GameObject("Circle on Car baby") {
        addComponent(new CircleVisualData(CircleShape(3), Color.black))
      }
      o.parent = gameObject
      o.parent = null

      has = true
    }
    gameObject.position += Vector2(0,5*delta)
}
