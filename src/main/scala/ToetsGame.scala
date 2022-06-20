import GameEnjin.Rendering.Drawer
import GameEnjin.Rendering.Swing.{SwingDrawer, TargetJPanel}

import javax.swing.{JFrame, WindowConstants}
import java.awt.Color
import scala.swing.*

object ToetsGame extends Game {
  // Setup game window:
  val jFrame = new JFrame()
  val tPanel = new TargetJPanel
  jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE)
  jFrame.add(tPanel)
  jFrame.pack()
  jFrame.setLocationRelativeTo(null)
  jFrame.setVisible(true)

  // Setup drawer:
//  val drawer = new SwingDrawer(skerm.graph)

  // Start game:
  var fc = 0
  startLoop(1)

  override def loop =
    super.loop
    fc += 1
    val g2d = tPanel.getGraphics.asInstanceOf[Graphics2D]

    g2d.setPaint(Color.blue)
    g2d.fillRect(10,10,170,70)
    if (fc > 3)
      running = false
      tPanel.clear
      g2d.drawString("Game has ended", 170, 70)
    else
      tPanel.clear
      g2d.drawString("Frame count: " + fc, 170, 70)
}
