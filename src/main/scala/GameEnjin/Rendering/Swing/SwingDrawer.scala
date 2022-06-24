package gameEnjin.rendering.swing

import gameEnjin.core.GameObject
import gameEnjin.geometry.{CircleShape, Vector2}
import gameEnjin.rendering.{Color, Drawer, ShapeVisuals}

import java.awt.Graphics
import javax.swing.{JFrame, JPanel, WindowConstants}
import scala.swing.{Dimension, Graphics2D}

class SwingDrawer extends Drawer {

  var _gameObjects: List[GameObject] = Nil
  var g2d: Graphics2D = null
  var k: Int = 0

  val jpanel: JPanel = new JPanel() {
    setPreferredSize(new Dimension(800, 500))

    override def paint(g: Graphics): Unit =
      super.paint(g)
      onPaint(g.asInstanceOf[Graphics2D])
  }
  val jframe = new JFrame()
  jframe.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE)
  jframe.add(jpanel)
  jframe.pack()
  jframe.setLocationRelativeTo(null)
  jframe.setVisible(true)


  private def onPaint(g: Graphics2D) =
    g2d = g
    g.drawString("hi", 30, 40)
    if (_gameObjects.nonEmpty)
      _gameObjects.foreach {
        (go: GameObject) =>
          if (go.hasComponent[ShapeVisuals])
            val sv = go.getComponent[ShapeVisuals]
            drawCricle(go.position, sv.shape.asInstanceOf[CircleShape].radius, sv.color)
      }


  override def draw(gameObjects: List[GameObject]): Unit =
    _gameObjects = gameObjects
    jpanel.repaint()

  override def drawCricle(position: Vector2, radius: Float, color: Color): Unit =
    g2d.setPaint(color.asAwtColor)
    g2d.fillOval(position.x.asInstanceOf[Int], position.y.asInstanceOf[Int], radius.asInstanceOf[Int], radius.asInstanceOf[Int])
}
