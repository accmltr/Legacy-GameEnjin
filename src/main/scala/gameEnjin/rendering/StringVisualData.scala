package gameEnjin.rendering

class StringVisualData(var text:String, var color: Color = Color.black) extends VisualData {
  override def draw(drawer: Drawer): Unit =
    drawer.drawString(gameObject.position + offset, text, color)
}
