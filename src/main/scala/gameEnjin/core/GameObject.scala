package gameEnjin.core

import gameEnjin.geometry.Vector2

import scala.reflect.ClassTag

class GameObject(var name: String) {
  private var _world: GameWorld = _
  private var _parent: GameObject = _
  private var _children: List[GameObject] = Nil
  var local_position: Vector2 = Vector2.zero
  var components: List[GameObjectComponent] = List.empty

  def world: GameWorld = _world
  def world_=(w:GameWorld): Unit =
    if (w == _world) return
    if (_world != null)
      if (!hasParent)
        _world.removeGameObject(this)
        _world == null
      else
        throw new Exception("ERROR: Trying to remove a game object (name: "+name+") with a parent from the world's game loop. " +
          "If this is the intention, remove the child from its parent first. " +
          "\n If you want to delete this game object instead, use its destroy() method.")
    if (w != null)
      _world = w
      if (!hasParent)
        _world.addGameObject(this)
        _children.foreach(_.world = w)
      else _parent.world = _world

  def parent = _parent
  def parent_=(newParent: GameObject): Unit =
    if (newParent == null) {
      if (hasParent)
        val prevParent = _parent
        _parent = null
        if (prevParent.children.contains(this)) prevParent.removeChild(this)
        world.addGameObject(this)
    }
    else if (!hasParent) {
      _parent = newParent
      if (!newParent.children.contains(this)) newParent.addChild(this)
      if (world != null) world.removeGameObject(this)
      world = _parent.world
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
