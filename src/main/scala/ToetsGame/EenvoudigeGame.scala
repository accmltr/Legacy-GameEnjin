package toetsGame

import gameEnjin.core.{GameObject, GameWorld}
import gameEnjin.geometry.{CircleShape, PolygonShape, Vector2}
import gameEnjin.physics.Rigidbody
import gameEnjin.rendering.{CircleVisualData, Color, PolygonVisualData}

object EenvoudigeGame extends GameWorld {

  val scene: List[GameObject] = List(
    new GameObject("Game Manager") {
      addComponent(new GameManager)
    })

  start(scene)
}
