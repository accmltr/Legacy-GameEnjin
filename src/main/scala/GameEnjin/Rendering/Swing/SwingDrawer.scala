package GameEnjin.Rendering.Swing

import GameEnjin.GameObject
import GameEnjin.Geometry.Vector2
import GameEnjin.Rendering.{Color, Drawer}

import java.awt.Graphics
import javax.swing.{JFrame, JPanel}
import scala.swing.{Dimension, Graphics2D}

class SwingDrawer extends Drawer {
  val jpanel = new JPanel() {
    setPreferredSize(new Dimension(800,500))

    override def paint(g: Graphics): Unit =
      super.paint(g)
      onPaint(g.asInstanceOf[Graphics2D])
  }
  val jframe = new JFrame()
  jframe.add(jpanel)
  jframe.pack()
  jframe.setLocationRelativeTo(null)
  jframe.setVisible(true)

  var _gameObjects: List[GameObject] = new List[GameObject]()

  private def onPaint(g2d: Graphics2D) =
    _gameObjects.foreach(println(_.name))


  override def draw(gameObjects: List[GameObject]): Unit =
    _gameObjects = gameObjects
    jpanel.repaint()

  override def drawCricle(position: Vector2, radius: Float, color: Color): Unit =
    println("Draw a %s circle at %s of radius: %f".format(color, position, radius))
}
