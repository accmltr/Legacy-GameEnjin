package gameEnjin.rendering.swing

import gameEnjin.core.GameObject
import gameEnjin.geometry._
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
    if (_gameObjects.nonEmpty)
      _gameObjects.foreach {
        (go: GameObject) =>
          if (go.hasComponent[ShapeVisuals])
            drawShapeVisuals(go.getComponent[ShapeVisuals])
      }


  override def draw(gameObjects: List[GameObject]): Unit =
    _gameObjects = gameObjects
    jpanel.repaint()

  def drawShapeVisuals(s: ShapeVisuals) =
    s.shape match
      case CircleShape(radius) =>
        drawCricle(s.gameObject.position, radius, s.color)
      case PolygonShape(points) =>
        drawPolygon(s.gameObject.position, points, s.color)




  override def drawCricle(position: Vector2, radius: Float, color: Color): Unit =
    g2d.setPaint(color.asAwtColor)
    g2d.fillOval(position.x.asInstanceOf[Int], position.y.asInstanceOf[Int], radius.asInstanceOf[Int], radius.asInstanceOf[Int])
  override def drawPolygon(position: Vector2, points: List[Vector2], color: Color): Unit =
    g2d.setPaint(color.asAwtColor)
    g2d.fillPolygon(points.map(p=>(p.x + position.x).toInt).toArray, points.map(p=>(p.y + position.y).toInt).toArray, points.length)

}
