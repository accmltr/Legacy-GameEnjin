package ToetsGame

import GameEnjin.*
import GameEnjin.Geometry.Vector2
import GameEnjin.Rendering.Swing.TargetJPanel

import java.awt.Graphics2D
import javax.swing.{JFrame, WindowConstants}

object RandomGame extends Game {
  val jFrame = new JFrame()
  val tPanel = new TargetJPanel
  tPanel.addOnPaintObserver(onPaint(_))
  jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE)
  jFrame.add(tPanel)
  jFrame.pack()
  jFrame.setLocationRelativeTo(null)
  jFrame.setVisible(true)

  // Start game:
  final val radius: Float = 20
  var pos: Vector2 = Vector2(radius, radius)
  var counter: Int = 0
  startLoop(1)

  def onPaint(graphics2D: Graphics2D): Unit = {
    println("hi" + counter)
  }

  override def loop =
    super.loop

    // Set onPaint callback to local draw method:
    //    tPanel.onPaint = (g2d: Graphics2D) => drawLoop(g2d)


    pos = pos + Vector2(1, 0)
    tPanel.repaint()
    counter += 1
}
