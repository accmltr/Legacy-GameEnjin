package GameEnjin.Physics

import GameEnjin.GameObject

class Physics {
  def step(gameObjects: List[GameObject], delta: Float): Unit =
  // Mutates the state of the physicsObjects
    for (o <- gameObjects.filter(_.hasComponent[PhysicsObject]))
      val p = o.getComponent[PhysicsObject]
      o.position += p.velocity * delta
      p.velocity += p.accelertion * delta
}