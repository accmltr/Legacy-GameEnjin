package gameEnjin.geometry

import gameEnjin.fMath.*

class Vector2(val x: Float, val y: Float) {
  def +(o: Vector2) = Vector2(x + o.x, y + o.y)
  def -(o: Vector2) = Vector2(x - o.x, y - o.y)
  def *(f: Float) = Vector2(x * f, y * f)
  def /(f: Float) = this * (1 / f)

  def length: Float = sqrt(x * x + y * y)
  def normalize = this / length
  /**
   * Return vector rotated by given angle.
   *
   * @param angle should be in radians
   */
  def rotated(angle: Float) = Vector2(x * cos(angle) - y * sin(angle), x * sin(angle) + y * cos(angle))
  /**
   * Returns angle of vector (in radians).
   */
  def angle: Float = math.atan2(y, x).toFloat

  override def equals(other: Any) = other match {
    case v: Vector2 => x == v.x && y == v.y
    case _ => false
  }
  override def toString = "(%f, %f)".format(x, y)
}

object Vector2 {
  def apply(x: Float, y: Float) = new Vector2(x, y)

  def zero = apply(0, 0)
  def ones = apply(1, 1)
  def left = apply(-1, 0)
  def right = apply(1, 0)
  def up = apply(0, 1)
  def down = apply(0, -1)
  /**
   * Creates a vector given an angle and a length.
   *
   * @param angle should be in radians
   */
  def fromAngle(angle: Float, length: Float = 1) = new Vector2(cos(angle) * length, sin(angle) * length)
}