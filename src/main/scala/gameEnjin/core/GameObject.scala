package gameEnjin.core

import gameEnjin.geometry.Vector2

import scala.reflect.ClassTag

class GameObject(var name: String) {
  private var _parent: GameObject = null
  private var _children: List[GameObject] = Nil
  var local_position: Vector2 = Vector2.zero
  var components: List[GameObjectComponent] = List.empty

  def parent = _parent
  def parent_=(newParent: GameObject): Unit =
    if (!hasParent) {
      _parent = newParent
      if (newParent.children.forall(_ != this)) newParent.addChild(this)
    }
    else if (newParent == null) {
      val prevParent = _parent
      _parent = null
      if (prevParent.children.exists(_ == this)) _parent.removeChild(this)
    }
    else if (hasParent)
      parent = null
      parent = newParent
  def destroy =
    if (hasParent) parent.removeChild(this)
    println("Queue GameObject for removal: " + name)
  def hasParent = parent != null
  def children =
    _children
  def addChild(o: GameObject) =
    _children = _children :+ o
    if (o.parent != this) o.parent = this
  def removeChild(c: GameObject) =
    _children = _children.filterNot(_ == c)
    c.local_position = c.position
    if (c.parent == this) c.parent = null
  def position: Vector2 =
    if (hasParent) parent.position + local_position else local_position
  def position_=(newPos: Vector2) =
    if (hasParent) local_position = newPos - parent.position else local_position = newPos
  def addComponent(c: GameObjectComponent) =
    components = components :+ c
    c.gameObject = this
  def deleteComponent(c: GameObjectComponent) =
    components = components.filter(_ != c)
  def hasComponent[T <: GameObjectComponent : ClassTag](implicit cls: ClassTag[T]): Boolean =
    components.exists(cls.runtimeClass.isInstance(_))
  def getComponent[T <: GameObjectComponent : ClassTag](implicit cls: ClassTag[T]): T =
    components.filter(cls.runtimeClass.isInstance(_)).map(_.asInstanceOf[T]).head
  def getComponents[T <: GameObjectComponent : ClassTag](implicit cls: ClassTag[T]): List[T] =
    components.filter(cls.runtimeClass.isInstance(_)).map(_.asInstanceOf[T])
  override def equals(obj: Any): Boolean = obj match
    //    case go: GameObject => this.id == go.id
    case _ => super.equals(obj)
}
