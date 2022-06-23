package GameEnjin.Physics

import GameEnjin.GameObjectComponent
import GameEnjin.Geometry.Vector2

case class PhysicsObject() extends GameObjectComponent{
  var velocity: Vector2 = Vector2(0, 0)
}
