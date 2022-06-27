package toetsGame

import gameEnjin.core.{GameObject, GameWorld}
import gameEnjin.geometry.{CircleShape, PolygonShape, Vector2}
import gameEnjin.physics.Rigidbody
import gameEnjin.rendering.{Color, ShapeVisuals}

object EenvoudigeGame extends GameWorld {

  val startingPoint: List[GameObject] = List(
    new GameObject("Race Ball 1") {
      position = Vector2(40, 80)
      addComponent(new ShapeVisuals(CircleShape(7.5), Color(.80, .30, .100)))
      addComponent(new Rigidbody {
        velocity = Vector2(10,0)
        accelertion = Vector2(10,0)
      })
    },
  new GameObject("Race Car 1") {
    position = Vector2(40, 110)
    addComponent(new ShapeVisuals(PolygonShape(List(Vector2(10, 5), Vector2(10, -5), Vector2(-10, -5), Vector2(-10, 5))), Color(.2, .3, .7)))
    addComponent(new Rigidbody {
      velocity = Vector2.zero
      accelertion = Vector2(12,0)
    })
    addComponent(new myscript)
    addChild(
    new GameObject("Race Car Window") {
      local_position = Vector2(3,0)
      addComponent(new ShapeVisuals(PolygonShape(List(Vector2(-2, -4), Vector2(-2, 4), Vector2(2, 4), Vector2(2, -4))), Color(.50, .50, 0)))
    })
    new GameObject("Race Car Window") {
      local_position = Vector2(-7,0)
      addComponent(new ShapeVisuals(PolygonShape(List(Vector2(-1, -4), Vector2(-1, 4), Vector2(1, 4), Vector2(1, -4))), Color(.50, .50, 0) * .9))
    }.parent = this
  },
    new GameObject("Race Car 2") {
      position = Vector2(40, 135)
      addComponent(new ShapeVisuals(PolygonShape(List(Vector2(10, 5), Vector2(12.5, 0), Vector2(10, -5), Vector2(-10, -6), Vector2(-10, 6))), Color(.2, .3, .7) * 1.7))
      addComponent(new Rigidbody {
        velocity = Vector2.zero
        accelertion = Vector2(12.1,0)
      })
    })

  start(startingPoint)
}
