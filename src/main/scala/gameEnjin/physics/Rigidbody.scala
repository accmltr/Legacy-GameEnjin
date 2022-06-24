package gameEnjin.physics

import gameEnjin.core.GameObjectComponent
import gameEnjin.geometry.Vector2

class Rigidbody extends GameObjectComponent{
  var velocity: Vector2 = Vector2()
  var accelertion: Vector2 = Vector2()
}
