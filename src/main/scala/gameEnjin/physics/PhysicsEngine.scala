package gameEnjin.physics

import gameEnjin.core.GameObject

class PhysicsEngine {
  def step(gameObjects: List[GameObject], delta: Float): Unit =
    for (o <- gameObjects.filter(_.hasComponent[Rigidbody]))
      val r = o.getComponent[Rigidbody]
      o.position += r.velocity * delta
      r.velocity += r.accelertion * delta
}