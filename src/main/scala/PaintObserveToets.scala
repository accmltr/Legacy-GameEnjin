import java.awt.Graphics
import javax.swing.{JFrame, JPanel}
import scala.swing.{Dimension, Graphics2D}

class TypedApp extends App {
  def onPaint(g: Graphics2D) = {}
}

object Test extends TypedApp {
  val t = this
  val frame = new JFrame()
  frame.add(new MyJPanel{lo = new ListeningObject; ta = t})
  frame.pack()
  frame.setVisible(true)
  frame.repaint()
  frame.repaint()
  frame.repaint()
  var x: Int = 1
  override def onPaint(g: Graphics2D): Unit =
    println("wasup " + x)
    x += 1
}

class ListeningObject {
  var x = 1
  def onPaint(g: Graphics2D) =
    g.drawString("X: " + x, 220, 170)
    x += 1
}

class MyJPanel extends JPanel {
  setPreferredSize(new Dimension(500, 350))
  var lo: ListeningObject = null
  var ta: TypedApp = null

  override def paint(g: Graphics) =
    super.paint(g)
    lo onPaint g.asInstanceOf[Graphics2D]
    ta onPaint g.asInstanceOf[Graphics2D]
}