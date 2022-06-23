package ToetsGame

import GameEnjin.{GameObject, GameWorld}
import GameEnjin.Geometry.{CircleShape, Vector2}
import GameEnjin.Physics.PhysicsObject
import GameEnjin.Rendering.{ShapeVisual, Color}

object EenvoudigeGame extends GameWorld {

  val startingPoint: List[GameObject] = List(
    new GameObject("Eerste Game Object") {
      position = Vector2(40, 80)
      addComponent(new ShapeVisual(new CircleShape(17), Color(.80, .30, .100)))
      addComponent(new PhysicsObject {
        velocity = Vector2(10)
        accelertion = Vector2(10)
      })
    },
    new GameObject("Eerstes Game Object") {
      position = Vector2(40, 100)
      addComponent(new ShapeVisual(new CircleShape(17), Color.black))
      addComponent(new PhysicsObject {
        velocity = Vector2()
        accelertion = Vector2(12)
      })
    })

  //  val o = startingPoint.head
  //  println("o.components: " + o.components)
  //  println("o.hasComponent[PhysOb]): "+o.hasComponent[PhysicsObject])
  //  println("o.hasComponent[ShapeVisual]): "+o.hasComponent[ShapeVisual])

  start(startingPoint,120)
}
