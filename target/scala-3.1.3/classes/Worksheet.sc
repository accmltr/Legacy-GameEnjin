
class ListHolder[T] {
  var _list: List[T] = Nil
  def list_=(l: List[T]) = _list = l
  def list = _list
}

val lh  = new ListHolder[Int]
lh.list
lh.list = List(1,2,3)
lh.list
var l = lh.list
l = List(3,2,1)
lh.list
lh.list = List(1,2,1)
lh.list
if (lh.list.head == 1)
  if (lh.list.head == 1)
    6
else if (lh.list.head == 2)
  7
else
  8