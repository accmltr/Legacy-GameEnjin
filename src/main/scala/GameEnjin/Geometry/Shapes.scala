package gameEnjin.geometry

trait Shape

case class CircleShape(radius: Float) extends Shape

case class PolygonShape(points: List[Vector2]) extends Shape