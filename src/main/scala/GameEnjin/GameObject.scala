package GameEnjin

import GameEnjin.Geometry.Vector2

class GameObject {
  var position: Vector2 = Vector2(0, 0)
  var components: List[GameObjectComponent] = List.empty

  def getComponent[T <: GameObjectComponent]: T =
    val r = getComponents[T]
    r.head

  def getComponents[T <: GameObjectComponent]: List[T] = components.filter(_.isInstanceOf[T]).map(_.asInstanceOf[T])
}
