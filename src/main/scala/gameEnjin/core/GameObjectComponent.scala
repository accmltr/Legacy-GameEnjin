package gameEnjin.core

trait GameObjectComponent {
  private var _gameObject: GameObject = null

  def setGameObject(o: GameObject) = _gameObject = o
  def gameObject = _gameObject
}
