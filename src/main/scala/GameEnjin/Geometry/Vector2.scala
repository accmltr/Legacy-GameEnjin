package GameEnjin.Geometry

final class Vector2(val x: Float, val y: Float) {
  def +(o: Vector2) = Vector2(x + o.x, y + o.y)
  def -(o: Vector2) = Vector2(x - o.x, y - o.y)
  def *(f: Float) = Vector2(x * f, y * f)
  def /(f: Float) = this * (1 / f)

  def length: Float = math.sqrt(x * x + y * y).asInstanceOf[Float]
  def normalize = this / length

  def angle = math.atan2(y, x)

  override def equals(other: Any) = other match {
    case v: Vector2 => x == v.x && y == v.y
    case _ => false
  }
  override def toString = "(%f, %f)".format(x, y)
}

object Vector2 {
  def apply(x: Float = 0, y: Float = 0) = new Vector2(x, y)
  def fromAngle(angle: Float, length: Float = 1) = new Vector2(math.cos(angle).asInstanceOf[Float] * length, math.sin(angle).asInstanceOf[Float] * length)
}