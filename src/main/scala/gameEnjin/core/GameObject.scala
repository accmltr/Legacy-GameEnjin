package gameEnjin.core

import gameEnjin.geometry.Vector2

import scala.reflect.ClassTag

class GameObject {
  /** Use this to give your game object a name. */
  var name: String = "Unnamed GameObject"
  private var _isDestroyed: Boolean = false
  private var _world: GameWorld = _
  private var _parent: GameObject = _
  private var _children: List[GameObject] = Nil
  /** This game object's position relative to its parent. */
  var local_position: Vector2 = Vector2.zero
  var components: List[GameObjectComponent] = List.empty

  /** Important check to see if you've destroyed this object or not. Trying to access a destroyed game object should be avoided,
   * and any member(property or method) of the GameObject class will throw an exception if you try to use it on a destroyed game object. */
  def isDestroyed: Boolean = _isDestroyed

  /**
   * Check used locally to throw Exception if someone accesses one of GameObject's members.
   */
  private def _destroy_check(): Unit =
    if (_isDestroyed) throw new Exception("Trying to access a destroyed GameObject: \'"+name+"\'.isDestroyed == true" +
      "\n If you would like to remove a GameObject from the game loop, but still keep it around, use GameObject.world = null. " +
      "And, if you would like to make it part of a game loop again, use GameObject.world = someWorld. (This can also be " +
      "used to move GameObjects from one world over to another)")

  /** Destroys this game object by removing it from the game world loop and its parents, and by setting isDestroyed to true
   * [see GameObject.isDestroyed for more info]. */
  def destroy(): Unit =
    _destroy_check()
    parent = null
    world = null
    _isDestroyed = true
    _children.foreach(_._isDestroyed = true)

  /** The world (GameWorld) this game object currently belongs to.
   *
   * If world is null, it means this game object won't receive any callbacks, like GameObject.update()*/
  def world: GameWorld =
    _destroy_check()
    _world

  /**
   * Use this to set the world of a game object, and make it part of that world's loop.
   *
   * Generally used to make newly created game objects a part of the game world, without having to give them parents
   * (when setting a game object's parent, their world is set to the parent's world automatically).
   *
   * It can also be used to temporarily remove game objects from your game world, or transfer them to another world. */
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

  /** Shorthand for: `this.world != null`*/
  def hasWorld: Boolean =
    _destroy_check()
    _world != null

  /** This game object's current parent.*/
  def parent: GameObject =
    _destroy_check()
    _parent

  /** Used to add game object to new parent (will automatically remove old one).
   *
   * Is analogous to:
   *
   * `parent = null; parent = newParent`
   *
   * and
   *
   * `parent.removeChild(this); newParent.addChild(this)`
   *
   * Setting parent to null, removes this game object from the parent and adds it as a parent-less game object to the
   * world it currently belongs to.
   * */
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
      children.foreach(_._world = _world)
    }
    else if (hasParent)
      parent = null
      parent = newParent

  /** Shorthand for: `this.parent != null`*/
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

  /** Removes child -the given game object- from this game object's children and adds the it to it's current game world,
   * as a parent-less game object.*/
  def removeChild(go: GameObject): Unit =
    _destroy_check()
    if (!hasChild(go)) return;
    _children = _children.filterNot(_ == go)
    go.local_position = go.position
    go.parent = null

  /** This game object's position in world space. */
  def position: Vector2 =
    _destroy_check()
    if (hasParent) parent.position + local_position else local_position

  /** Set this game object's global/world position. */
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
