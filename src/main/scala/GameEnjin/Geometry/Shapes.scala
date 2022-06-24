package gameEnjin.geometry

trait Shape

case class CircleShape(val radius: Float) extends Shape

case class PolygonShape(val points: List[Vector2]) extends Shape