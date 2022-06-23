package GameEnjin.Physics

import GameEnjin.GameObjectComponent
import GameEnjin.Geometry.Vector2

class PhysicsObject extends GameObjectComponent{
  var velocity: Vector2 = Vector2()
  var accelertion: Vector2 = Vector2()
}
