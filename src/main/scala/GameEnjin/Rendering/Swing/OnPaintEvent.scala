package GameEnjin.Rendering.Swing

trait OnPaintEvent[S] {
  private var observers: List[S => Unit] = Nil

  def addOnPaintObserver(observer: S => Unit) = observers = observer :: observers

  def notifyOnPaintObservers(s: S) = observers.foreach(_.apply(s))
}
