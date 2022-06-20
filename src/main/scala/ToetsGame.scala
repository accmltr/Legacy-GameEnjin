import Rendering.{Drawer, SwingDrawer}

import scala.swing.*

object ToetsGame extends Game {
  // Setup game window:
  val skerm = new Skerm("Toets Game")
  skerm.contents = new Label("Game is running")

  // Setup drawer:
  val drawer = new SwingDrawer(skerm.graph)

  // Start game:
  var fc = 0
  startLoop(1)

  override def loop =
    super.loop
    fc += 1
    skerm.contents = new Label("Frame number: " + fc)
    if (fc > 3)
      running = false
      skerm.contents = new BoxPanel(Orientation.Vertical) {
        contents += new Label("Game has ended")
        contents += Button("Exit")(System.exit(0))
      }
}
