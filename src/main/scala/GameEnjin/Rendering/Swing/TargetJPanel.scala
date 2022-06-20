package GameEnjin.Rendering.Swing

import java.awt.{BasicStroke, Color, Dimension, Graphics, Graphics2D}
import javax.swing.JPanel

class TargetJPanel extends JPanel {
  setPreferredSize(new Dimension(800, 450))

  def clear = getGraphics.clearRect(0, 0, getWidth, getHeight)
}
