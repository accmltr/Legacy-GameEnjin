package gameEnjin.rendering

import gameEnjin.geometry.PolygonShape

class PolygonVisualData(var polygon: PolygonShape, var color: Color) extends VisualData {
  override def draw(drawer: Drawer): Unit =
    drawer.drawPolygon(gameObject.position + offset, polygon, color)
}
