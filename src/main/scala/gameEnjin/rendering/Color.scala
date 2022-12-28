package gameEnjin.rendering

import gameEnjin.fMath.clamp


class Color(val r: Float, val g: Float, val b: Float) {
  def *(f: Float) = Color(clamp(f*r, 0,1), clamp(f*g, 0,1), clamp(f*b, 0,1))
  def asAwtColor = new java.awt.Color(r,g,b)
  override def toString: String = "(%f, %f, %f)".format(r, g, b)
}


object Color {
  def apply(r: Float, g: Float, b: Float) = new Color(r, g, b)
  def black = Color(0, 0, 0)
  def white = Color(1,1,1)
}