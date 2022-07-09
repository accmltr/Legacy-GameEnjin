package toetsGame

import gameEnjin.core.{GameObject, GameWorld}
import gameEnjin.geometry.{CircleShape, PolygonShape, Vector2}
import gameEnjin.physics.Rigidbody
import gameEnjin.rendering.{CircleVisualData, Color, PolygonVisualData, StringVisualData}

object EenvoudigeGame extends GameWorld {

  val scene: List[GameObject] = List(
    new GameObject {
      name = "Game Manager"
      addComponent(new GameManager)
      addComponent(new StringVisualData("-Resies Game-"){offset = Vector2(360,10)})
    })

  start(scene)
}
