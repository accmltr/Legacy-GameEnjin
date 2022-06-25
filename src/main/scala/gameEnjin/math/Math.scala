package gameEnjin.math

object Math {
  def clamp(v: Float, min: Float, max: Float) = math.min(math.max(min, v), max)
}
