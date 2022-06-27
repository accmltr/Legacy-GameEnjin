package toetsGame

import gameEnjin.core.GameObjectComponent
import gameEnjin.geometry.Vector2

class myscript extends GameObjectComponent{
  override def update(delta: Float): Unit =
    gameObject.position += Vector2(0,5*delta)
}
