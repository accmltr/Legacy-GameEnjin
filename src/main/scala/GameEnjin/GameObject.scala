package GameEnjin

import GameEnjin.Geometry.Vector2

import scala.reflect.ClassTag

class GameObject(var name: String) {
  var position: Vector2 = Vector2(0, 0)
  var components: List[GameObjectComponent] = List.empty

  def addComponent(c: GameObjectComponent) = components = components :+ c
  def hasComponent[T <: GameObjectComponent : ClassTag]: Boolean = components.exists(_.isInstanceOf[T])
  def getComponent[T <: GameObjectComponent]: T =
    val r = getComponents[T]
    r.head
  def getComponents[T <: GameObjectComponent]: List[T] = components.filter(_.isInstanceOf[T]).map(_.asInstanceOf[T])
}
