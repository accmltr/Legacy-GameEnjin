package gameEnjin.core

trait GameObjectComponent {
  private var _gameObject: GameObject = null

  def update(delta: Float) = {}
  def gameObject = _gameObject
  def gameObject_=(o: GameObject) = 
    if (_gameObject == null) _gameObject = o else throw Exception("Trying to set gameObject property on a GO component that already belongs to a gameObject.")
}
