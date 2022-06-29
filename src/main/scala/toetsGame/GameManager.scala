package toetsGame

import gameEnjin.core.{GameObject, GameObjectComponent}
import gameEnjin.geometry.{CircleShape, PolygonShape, Vector2}
import gameEnjin.rendering.{CircleVisualData, Color}

import scala.util.Random

class GameManager extends GameObjectComponent {
  var has = false
  var racerCount: Int = 5
  var racerSpacing: Float = 20
  var leftMargin: Float = 20

  var karretjies: List[Karretjie] = Nil

  override def update(delta: Float): Unit =
    if (!has) {
      // Do _ready() code:
      println(gameObject.name + " is ready!")
      println("world.FPS = " + gameObject.world.FPS)
      println("world.physics = " + gameObject.world.physics)

      val varianceF: Float = 5
      for (i <- 1 to racerCount)
      // Add a racer:
        val kar = new Karretjie {
          name = "Kar " + i
          speed += Random.between(-varianceF, varianceF)
          position = Vector2(leftMargin, i * racerSpacing)
          parent = gameObject
        }
        kar.gameManager = this
        karretjies=  kar:: karretjies

      has = true
    }

  def win(kar: Karretjie): Unit =
    println(kar.name + " het gewen!")
    karretjies.foreach(_.stopped = true)
    // Delete al die karre:
//    karretjies.foreach{ k =>
//      k.parent = null
//    }
//    karretjies = Nil
}
