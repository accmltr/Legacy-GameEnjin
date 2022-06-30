package toetsGame

import gameEnjin.core.{GameObject, GameObjectComponent}
import gameEnjin.geometry.{CircleShape, PolygonShape, Vector2}
import gameEnjin.rendering.{CircleVisualData, Color, PolygonVisualData}

import scala.util.Random

class GameManager extends GameObjectComponent {
  var has = false
  var racerCount: Int = 5
  var racerSpacing: Float = 20
  var leftMargin: Float = 20
  var finishLine: Int = 300
  var winCount: Int = 0

  var karretjies: List[Karretjie] = Nil

  override def update(delta: Float): Unit =
    if (!has) {
      // Do _ready() code:
      println(gameObject.name + " is ready!")

      // Add finish line visuals:
      gameObject.addComponent(new PolygonVisualData(
        PolygonShape(
          List(Vector2(finishLine, 0),Vector2(finishLine+5, 0),
            Vector2(finishLine+5, racerSpacing*(racerCount+1)),Vector2(finishLine, racerSpacing*(racerCount+1)))
        ),
        Color(1,.5,0)))

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
        kar.finishLine = finishLine
        karretjies = kar :: karretjies

      has = true
    }

  def win(kar: Karretjie): Unit =
    winCount += 1
    println(kar.name + " het " + winCount + "de gekom!")
    kar.destroy()
}
