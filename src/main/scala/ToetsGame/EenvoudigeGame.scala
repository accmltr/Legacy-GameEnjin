package toetsGame

import gameEnjin.core.{GameObject, GameWorld}
import gameEnjin.geometry.{CircleShape, Vector2}
import gameEnjin.physics.Rigidbody
import gameEnjin.rendering.{ShapeVisuals, Color}

object EenvoudigeGame extends GameWorld {

  val startingPoint: List[GameObject] = List(
    new GameObject("Eerste Game Object") {
      position = Vector2(40, 80)
      addComponent(new ShapeVisuals(new CircleShape(17), Color(.80, .30, .100)))
      addComponent(new Rigidbody {
        velocity = Vector2(10)
        accelertion = Vector2(10)
      })
    },
    new GameObject("Eerstes Game Object") {
      position = Vector2(40, 100)
      addComponent(new ShapeVisuals(new CircleShape(17), Color.black))
      addComponent(new Rigidbody {
        velocity = Vector2()
        accelertion = Vector2(12)
      })
    })

  //  val o = startingPoint.head
  //  println("o.components: " + o.components)
  //  println("o.hasComponent[PhysOb]): "+o.hasComponent[PhysicsObject])
  //  println("o.hasComponent[ShapeVisual]): "+o.hasComponent[ShapeVisual])

  start(startingPoint)
}
