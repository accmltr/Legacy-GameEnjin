package gameEnjin.rendering.swing

import gameEnjin.core.{GameObject, GameWorld}
import gameEnjin.geometry.*
import gameEnjin.rendering.*

import java.awt.Graphics
import javax.swing.{JFrame, JPanel, WindowConstants}
import scala.swing.{Dimension, Graphics2D}

class SwingDrawer(world: GameWorld) extends Drawer(world) {

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


  private def onPaint(graphics2D: Graphics2D): Unit =
    _g2d = graphics2D
    gameEnjin.utils.forAllGameObjectsAndChildren(
      _gameObjects,
      (go: GameObject) => {
        go.getComponents[VisualData].foreach(_.draw(this))
        true
      },
      world)

  override def draw(gameObjects: List[GameObject]): Unit =
    _gameObjects = gameObjects
    jpanel.repaint()

  override def drawCricle(position: Vector2, radius: Float, color: Color): Unit =
    _g2d.setPaint(color.asAwtColor)
    _g2d.fillOval((position.x - radius).toInt, (position.y - radius).toInt, (2 * radius).toInt, (2 * radius).toInt)

  override def drawPolygon(position: Vector2, polygon: PolygonShape, color: Color): Unit =
    _g2d.setPaint(color.asAwtColor)

    def _params(pts: List[Vector2], pxs: List[Int] = Nil, pys: List[Int] = Nil, n: Int = 0): (Array[Int], Array[Int], Int) =
      if (pts.isEmpty) (pxs.toArray, pys.toArray, n)
      else _params(pts.tail, (pts.head.x + position.x).toInt :: pxs, (pts.head.y + position.y).toInt :: pys, n + 1)

    val params = _params(polygon.points)
    _g2d.fillPolygon(params._1, params._2, params._3)
}
