package gameEnjin.core

import gameEnjin.geometry.{CircleShape, Vector2}
import gameEnjin.physics.PhysicsEngine
import gameEnjin.rendering.swing.SwingDrawer
import gameEnjin.rendering.{CircleVisualData, Color, Drawer}

import scala.annotation.tailrec
import scala.swing.Graphics2D

class GameWorld() extends App {

  val physics: PhysicsEngine = new PhysicsEngine
  val drawer: Drawer = new SwingDrawer(this)

  private var _FPS: Int = 60
  private var _frameDuration: Long = (1000000000 / _FPS.toDouble).toLong // The desired frame duration, in nano-seconds.
  private var _running: Boolean = false
  private var _gameObjectsToAdd: List[GameObject] = Nil // Only adds to top level of scene, not children
  private var _gameObjectsToRemove: List[GameObject] = Nil // Only removes from top level of scene, not children

  /**
   * Add game objects to the world, to be part of the game loop -- meaning they will receive callbacks from the engine, like GameObject.update().
   *
   * Only necessary for game objects that don't have parents. Childing an object to
   */
  def addGameObject(go: GameObject): Unit =
    if (go.hasParent)
      throw new Exception("ERROR: Trying to set world on a game object with a parent (name: " + go.name + "). " +
        "If this is the intention, remove the child from its parent first. ")
    if (!_gameObjectsToAdd.contains(go))
      if (_gameObjectsToRemove.contains(go))
        _gameObjectsToRemove = _gameObjectsToRemove.filterNot(_ == go)
      else
        _gameObjectsToAdd = go :: _gameObjectsToAdd
      go.world = this

  /**
   * Remove game objects from the world, they wont be part of the game loop anymore.
   */
  def removeGameObject(go: GameObject): Unit =
    if (go.hasParent) throw new Exception("ERROR: Trying to remove world on a game object with a parent (name: " + go.name + "). " +
      "If this is the intention, remove the child from its parent first. " +
      "\n If you want to delete this game object instead, use its destroy() method.")
    if (!_gameObjectsToRemove.contains(go))
      if (_gameObjectsToAdd.contains(go))
        _gameObjectsToAdd = _gameObjectsToAdd.filterNot(_ == go)
      else
        _gameObjectsToRemove = go :: _gameObjectsToRemove
      go.world = null
      println("Removed " + go.name)

  def start(scene: List[GameObject]): Unit =
    if (_running) throw new Exception("ERROR: Trying to .start() on game world that is already running.")
    _running = true
    // Set the "world" property on all game objects in "scene":
    // (using "_gameObjectsToAdd" instead of "scene", in case there is a callback in the future
    // for GameObject.onWorldSet, where code could add more game objects to the world before loop() runs.)
    scene.foreach(_.world = this)
    val temp = _gameObjectsToAdd
    _gameObjectsToAdd = Nil
    loop(temp)

  @tailrec
  private def loop(scene: List[GameObject], lastStartTime: Long = System.nanoTime()): Unit =

    def filterScene =
      val r = scene.filterNot(_gameObjectsToRemove.contains(_)) ::: _gameObjectsToAdd
      _gameObjectsToRemove = Nil
      _gameObjectsToAdd = Nil
      r

    // Stop game:
    if (!_running) return

    val frameStartTime = System.nanoTime()
    val deltaTime: Float = (frameStartTime - lastStartTime) / 1000000000.0f

    // Run game:
    physics.step(scene, deltaTime)
    gameEnjin.utils.forAllGameObjectsAndChildren(scene,
      (o: GameObject) => {
        if (!_running) println("World HAS STOPPED")
        if (_gameObjectsToRemove.contains(o)) // Make sure not to process objects just deleted by components.
          println("Found object to that is removed! -> " + o.name)
          false // Don't move on to processing this game object's children with this function. [Look at utils.forAllGameObjectsAndChildren for more information]
        else
          @tailrec
          def _update_components(cRemaining: List[GameObjectComponent], dTime: Float): Boolean = {
            if (cRemaining.isEmpty) return true
            cRemaining.head._special_access_update(deltaTime)
            if (o.isDestroyed)
              return false
            if (o.world != this)
              return false
            if (!_running)
              println("Die wereld het gestop.")
            _update_components(cRemaining.tail, dTime)
          }

          _update_components(o.components, deltaTime)
      },
      this
    )
    val filteredScene = filterScene // Adds and removes game objects from loop
    drawer.draw(filteredScene)
    //

    // Sleep until next frame needs to be started:
    val sleepTime = (_frameDuration - System.nanoTime() + frameStartTime) / 1000000 // in milli-seconds
    if (sleepTime > 0) Thread.sleep(sleepTime) //else println("Frame took longer than set desiredFrameDuration to compute.")


    //
    // Run next loop:
    loop(filteredScene, frameStartTime)


  /**
   *  Stops the game world at the end of the current frame.
   */
  def stop(): Unit = _running = false

  def running = _running

  def FPS = _FPS

  def FPS_=(fps: Int): Unit =
    _FPS = fps
    _frameDuration = (1000000000 / _FPS.toDouble).toLong
}