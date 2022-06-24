package gameEnjin.rendering


class Color(val r: Float, val g: Float, val b: Float) {
  override def toString: String = "(%f, %f, %f)".format(r, g, b)
  def asAwtColor = new java.awt.Color(r,g,b)
}


object Color {
  def apply(r: Float, g: Float, b: Float) = new Color(r, g, b)

  def black = Color(0, 0, 0)
}