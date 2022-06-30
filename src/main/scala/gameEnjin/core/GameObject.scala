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

  def _destroy_check(): Unit =
    if (_isDestroyed) throw new Exception("Trying to access a destroyed GameObject: \'"+name+"\'.isDestroyed == true" +
      "\n If you would like to remove a GameObject from the game loop, but still keep it around, use GameObject.world = null. " +
      "And, if you would like to make it part of a game loop again, use GameObject.world = someWorld. (This can also be " +
      "used to move GameObjects from one world over to another)")

  def destroy(): Unit =
    _destroy_check()
    parent = null
    world = null
    _children.foreach(_._isDestroyed = true)
    _isDestroyed = true

  def world: GameWorld =
    _destroy_check()
    _world

  def world_=(newWorld: GameWorld): Unit =
    _destroy_check()
    if (newWorld == _world) return;
    if (hasParent) parent = null
    if (hasWorld)
      val previousWorld = _world
      _world = null
      previousWorld.removeGameObject(this)
      _children.foreach(_._world = null)
    if (newWorld != null)
      _world = newWorld
      _world.addGameObject(this)
      _children.foreach(_._world = newWorld)

  def hasWorld: Boolean =
    _destroy_check()
    _world != null

  def parent: GameObject =
    _destroy_check()
    _parent

  def parent_=(newParent: GameObject): Unit =
    _destroy_check()
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
      if (hasWorld)
        world.removeGameObject(this)
      _world = _parent.world
    }
    else if (hasParent)
      parent = null
      parent = newParent

  def hasParent: Boolean =
    _destroy_check()
    parent != null

  def children: List[GameObject] =
    _destroy_check()
    _children

  def hasChild(go: GameObject): Boolean =
    _destroy_check()
    children.contains(go)

  def addChild(go: GameObject): Unit =
    _destroy_check()
    if (hasChild(go)) return;
    _children = _children :+ go
    go.parent = this

  def removeChild(go: GameObject): Unit =
    _destroy_check()
    if (!hasChild(go)) return;
    _children = _children.filterNot(_ == go)
    go.local_position = go.position
    go.parent = null

  def position: Vector2 =
    _destroy_check()
    if (hasParent) parent.position + local_position else local_position

  def position_=(newPos: Vector2): Unit =
    _destroy_check()
    if (hasParent) local_position = newPos - parent.position else local_position = newPos

  def addComponent(c: GameObjectComponent): Unit =
    _destroy_check()
    components = components :+ c
    c.gameObject = this

  def deleteComponent(c: GameObjectComponent): Unit =
    _destroy_check()
    components = components.filter(_ != c)

  def hasComponent[T <: GameObjectComponent : ClassTag](implicit cls: ClassTag[T]): Boolean =
    _destroy_check()
    components.exists(cls.runtimeClass.isInstance(_))

  def getComponent[T <: GameObjectComponent : ClassTag](implicit cls: ClassTag[T]): T =
    _destroy_check()
    components.filter(cls.runtimeClass.isInstance(_)).map(_.asInstanceOf[T]).head

  def getComponents[T <: GameObjectComponent : ClassTag](implicit cls: ClassTag[T]): List[T] =
    _destroy_check()
    components.filter(cls.runtimeClass.isInstance(_)).map(_.asInstanceOf[T])

  override def equals(obj: Any): Boolean = obj match
    //    case go: GameObject => this.id == go.id
    case _ => super.equals(obj)
}
