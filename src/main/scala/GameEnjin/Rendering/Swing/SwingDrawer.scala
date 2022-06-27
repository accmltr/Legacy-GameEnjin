package gameEnjin.rendering.swing

import gameEnjin.core.GameObject
import gameEnjin.geometry._
import gameEnjin.rendering.{Color, Drawer, ShapeVisuals}

import java.awt.Graphics
import javax.swing.{JFrame, JPanel, WindowConstants}
import scala.swing.{Dimension, Graphics2D}

class SwingDrawer extends Drawer {

  var _gameObjects: List[GameObject] = Nil
  var _g2d: Graphics2D = null
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


  private def onPaint(graphics2D: Graphics2D) =
    _g2d = graphics2D
    _drawGameObjects(_gameObjects)
    def _drawGameObjects(l: List[GameObject]): Unit =
      l.foreach { (go: GameObject) =>
        go.components.foreach { c =>
          if (c.isInstanceOf[ShapeVisuals]) drawShapeVisuals(c.asInstanceOf[ShapeVisuals])
        }
        _drawGameObjects(go.children)
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
    _g2d.setPaint(color.asAwtColor)
    _g2d.fillOval((position.x - radius).toInt, (position.y - radius).toInt, (2 * radius).toInt, (2 * radius).toInt)
  override def drawPolygon(position: Vector2, points: List[Vector2], color: Color): Unit =
    _g2d.setPaint(color.asAwtColor)
    _g2d.fillPolygon(points.map(p => (p.x + position.x).toInt).toArray, points.map(p => (p.y + position.y).toInt).toArray, points.length)

}
