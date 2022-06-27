package gameEnjin.rendering

import gameEnjin.core.GameObjectComponent
import gameEnjin.geometry.Vector2

trait VisualData extends GameObjectComponent {
  var offset: Vector2 = Vector2.zero
  def draw(drawer: Drawer): Unit
}
