package gameEnjin

import gameEnjin.core.{GameObject, GameWorld}

package object utils {

  /**
   * Used to go through a scene (list of GameObjects) and process them and their children, grand-children etc, with a given function.
   * @param f Function used to process each game object, will only continue on to process children if it return true.
   * @param world Used to see if game objects still belong to the same world, and should still be processed.
   */
  def forAllGameObjectsAndChildren(gameObjects: List[GameObject], f: GameObject => Boolean, world: GameWorld): Unit =
    gameObjects.foreach { o =>
      val continue = f(o)
      if (continue)
        if (!o.isDestroyed)
          if (o.world == world)
            forAllGameObjectsAndChildren(o.children, f,world)
    }
}
