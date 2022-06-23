import GameEnjin.{GameObject, GameObjectComponent}
import GameEnjin.Geometry.{CircleShape, Vector2}
import GameEnjin.Physics.PhysicsObject
import GameEnjin.Rendering.{Color, ShapeVisual}

import scala.reflect.ClassTag

case class pizza(var x: Int)
val p1 = pizza(2)
p1.x = 3
p1.x

val o = new GameObject("Eerste Game Object") {
  position = Vector2(40, 80)
  addComponent(new ShapeVisual(new CircleShape(17), Color.black))
}
def hasComponent[T]: Boolean =
  o.components.exists(_.isInstanceOf[T])

o.components.exists(_.isInstanceOf[PhysicsObject])
o.hasComponent[PhysicsObject]
hasComponent[PhysicsObject]
o.getComponent[PhysicsObject]

var l = List("Some string", 3)
def containsType[T : ClassTag] =
  l.collect{case t: T => t}
containsType[Int]
l.exists(_.isInstanceOf[Boolean])