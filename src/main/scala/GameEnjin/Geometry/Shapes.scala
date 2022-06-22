package GameEnjin.Geometry

trait Shape

class CircleShape(val radius: Float) extends Shape

class PolygonShape(val points: List[Vector2]) extends Shape