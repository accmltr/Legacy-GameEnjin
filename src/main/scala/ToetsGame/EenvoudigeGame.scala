package toetsGame

import gameEnjin.core.{GameObject, GameWorld}
import gameEnjin.geometry.{CircleShape, PolygonShape, Vector2}
import gameEnjin.physics.Rigidbody
import gameEnjin.rendering.{Color, ShapeVisuals}

object EenvoudigeGame extends GameWorld {

  val startingPoint: List[GameObject] = List(
    new GameObject("Race Ball 1") {
      position = Vector2(40, 80)
      addComponent(new ShapeVisuals(CircleShape(17), Color(.80, .30, .100)))
      addComponent(new Rigidbody {
        velocity = Vector2(10)
        accelertion = Vector2(10)
      })
    },
    new GameObject("Race Car 1") {
      position = Vector2(40, 110)
      addComponent(new ShapeVisuals(PolygonShape(List(Vector2(10,5), Vector2(10,-5), Vector2(-10,-5), Vector2(-10,5))), Color(.2,.3,.7)))
      addComponent(new Rigidbody {
        velocity = Vector2()
        accelertion = Vector2(12)
      })
    },
    new GameObject("Race Car 2") {
      position = Vector2(40, 135)
      addComponent(new ShapeVisuals(PolygonShape(List(Vector2(10,5), Vector2(10,-5), Vector2(-10,-5), Vector2(-10,5))), Color(.2,.3,.7) * 1.7))
      addComponent(new Rigidbody {
        velocity = Vector2()
        accelertion = Vector2(12.1)
      })
    })

  start(startingPoint)
}
