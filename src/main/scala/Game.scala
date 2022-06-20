class Game extends App {
  var running: Boolean = true
  var lastFrame: Long = 0

  def startLoop(FPS: Int = 60) =
    val frameDuration: Long = math.pow(10, 9).asInstanceOf[Long] / FPS
    while (running)
      if (System.nanoTime() - lastFrame > frameDuration)
        lastFrame = System.nanoTime()
        loop
        Thread.sleep((frameDuration - System.nanoTime() + lastFrame) / 1000000)
    println("Game ended")

  def loop = {}
}