package GameEnjin.Rendering.Swing

import GameEnjin.*

import java.awt.{BasicStroke, Color, Dimension, Graphics, Graphics2D}
import javax.swing.JPanel

class TargetJPanel extends JPanel with OnPaintEvent[Graphics2D] {
  setPreferredSize(new Dimension(800, 450))

  override def paint(g: Graphics): Unit =
    super.paint(g)
    notifyOnPaintObservers(g.asInstanceOf[Graphics2D])

  def clear = getGraphics.clearRect(0, 0, getWidth, getHeight)
}
