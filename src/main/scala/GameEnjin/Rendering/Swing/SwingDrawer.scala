package GameEnjin.Rendering.Swing

import GameEnjin.Geometry.Vector2
import GameEnjin.Rendering.{Color, Drawer}

import java.awt.Graphics
import javax.swing.JPanel
import scala.swing.{Dimension, Graphics2D}

class SwingDrawer extends Drawer {
  val jpanel = new JPanel() {
    setPreferredSize(new Dimension(800,500))

    override def paint(g: Graphics): Unit =
      super.paint(g)
      onPaint(g.asInstanceOf[Graphics2D])
  }

  private def onPaint(g2d: Graphics2D) =
    g2d.setPaint(java.awt.Color.orange)
    g2d.drawString("Drawing", 50, 50)

  override def draw: Unit =
    jpanel.repaint()

  override def drawCricle(position: Vector2, radius: Float, color: Color): Unit =
    println("Draw a %s circle at %s of radius: %f".format(color, position, radius))
}
