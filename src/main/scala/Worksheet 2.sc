class Object {
  var localVariableState: Int = 7

  def react = println(localVariableState)
}

object ObjectInstance extends Object

class EventManager {
  var targetObjects: List[Object] = List(new Object, new Object)

  def printObjectVars = targetObjects foreach(_.react)
}

val eventManager = new EventManager
eventManager.printObjectVars
eventManager.targetObjects.last.localVariableState += 1
eventManager.printObjectVars
eventManager.targetObjects = eventManager.targetObjects.tail
eventManager.printObjectVars
eventManager.targetObjects = ObjectInstance :: eventManager.targetObjects
eventManager.printObjectVars
ObjectInstance.localVariableState += 3
eventManager.printObjectVars