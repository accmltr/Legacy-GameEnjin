package gameEnjin.rendering

import gameEnjin.geometry.{CircleShape, Shape, Vector2}

class CircleVisualData(var circle: CircleShape, var color: Color) extends VisualData {
  def draw(drawer: Drawer) =
    drawer.drawCricle(gameObject.position + offset, circle.radius, color)
}
