package gameEnjin.physics

import gameEnjin.core.GameObject

class Physics {
  def step(gameObjects: List[GameObject], delta: Float): Unit =
  // Mutates the state of the physicsObjects
    for (o <- gameObjects.filter(_.hasComponent[Rigidbody]))
      val r = o.getComponent[Rigidbody]
      o.position += r.velocity * delta
      r.velocity += r.accelertion * delta
}