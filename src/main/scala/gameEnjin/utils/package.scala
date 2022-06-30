package gameEnjin

import gameEnjin.core.{GameObject, GameWorld}

package object utils {
  def forAllGameObjectsAndChildren(gameObjects: List[GameObject], f: GameObject => Unit, world: GameWorld): Unit =
    gameObjects.foreach { o =>
      f(o)
      if (o.world == world)
        forAllGameObjectsAndChildren(o.children, f,world)
    }
}
