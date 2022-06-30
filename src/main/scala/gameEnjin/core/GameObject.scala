package gameEnjin.core

import gameEnjin.geometry.Vector2

import scala.reflect.ClassTag

class GameObject {
  var name: String = "Unnamed GameObject"
  private var _isDestroyed: Boolean = false
  private var _world: GameWorld = _
  private var _parent: GameObject = _
  private var _children: List[GameObject] = Nil
  var local_position: Vector2 = Vector2.zero
  var components: List[GameObjectComponent] = List.empty

  def isDestroyed: Boolean = _isDestroyed

  def destroy(): Unit =
    _isDestroyed = true
    parent = null
    world = null

  def world: GameWorld = _world

  def world_=(w: GameWorld): Unit =
    if (w == _world) return;
    if (hasWorld)
      val previousWorld = _world
      _world = null
      if (!hasParent) previousWorld.removeGameObject(this)
      _children.foreach(_.world = null)
    if (w != null)
      _world = w
      if (!hasParent)_world.addGameObject(this)
      _children.foreach(_.world = w)

  def hasWorld: Boolean = _world != null

  def parent: GameObject = _parent

  def parent_=(newParent: GameObject): Unit =
    if (parent == newParent) return;
    if (newParent == null) {
      if (hasParent)
        val prevParent = _parent
        _parent = null
        prevParent.removeChild(this)
        world.addGameObject(this)
    }
    else if (!hasParent) {
      _parent = newParent
      newParent.addChild(this)
      if (world == newParent.world)
        world.removeGameObject(this)
      else
        world = _parent.world
    }
    else if (hasParent)
      parent = null
      parent = newParent

  def hasParent: Boolean = parent != null

  def children: List[GameObject] = _children

  def hasChild(go: GameObject): Boolean = children.contains(go)

  def addChild(go: GameObject): Unit =
    if (hasChild(go)) return;
    _children = _children :+ go
    go.parent = this

  def removeChild(go: GameObject): Unit =
    if (!hasChild(go)) return;
    _children = _children.filterNot(_ == go)
    go.local_position = go.position
    go.parent = null

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
