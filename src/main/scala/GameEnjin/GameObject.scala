package GameEnjin

import GameEnjin.Geometry.Vector2

import scala.reflect.ClassTag

class GameObject(var name: String) {
  var position: Vector2 = Vector2(0, 0)
  var components: List[GameObjectComponent] = List.empty

  def addComponent(c: GameObjectComponent) = components = components :+ c
  def hasComponent[T <: GameObjectComponent : ClassTag](implicit cls: ClassTag[T]): Boolean = components.exists(cls.runtimeClass.isInstance(_))
  def getComponent[T <: GameObjectComponent : ClassTag](implicit cls: ClassTag[T]): T =
    components.filter(cls.runtimeClass.isInstance(_)).map(_.asInstanceOf[T]).head
  def getComponents[T <: GameObjectComponent : ClassTag](implicit cls: ClassTag[T]): List[T] = components.filter(cls.runtimeClass.isInstance(_)).map(_.asInstanceOf[T])
}
