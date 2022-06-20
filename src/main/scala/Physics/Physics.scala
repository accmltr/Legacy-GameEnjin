package Physics

class Physics {
  def step(physicsObjects: List[PhysicsObject], delta: Float = 1.0f / 60): Unit =
  // Mutates the state of the physicsObjects
    for (o <- physicsObjects)
      o.position += o.velocity * delta
}