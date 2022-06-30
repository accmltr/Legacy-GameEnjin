package gameEnjin

import gameEnjin.core.GameObject

package object utils {
  def forAllGameObjectsAndChildren(gameObjects: List[GameObject], f: GameObject => Unit): Unit =
    gameObjects.foreach { o =>
      if (!o.world.running) println("World HAS STOPPED")
      if (o.isDestroyed) println("Found a destroyed object! -> "+o.name)
      if (!o.isDestroyed)
        f(o)
        forAllGameObjectsAndChildren(o.children, f)
    }
}
