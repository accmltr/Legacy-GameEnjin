trait Animal
case class Dog() extends Animal
case class Cat() extends Animal

val animals: List[Animal] = List(Dog(), Dog())

def containsAnimalSubtype[T](l: List[Animal]) =
  l.exists( (a: Animal) =>
    a match
      case _: T => true
      case _ => false
  )

containsAnimalSubtype[Dog](animals)
containsAnimalSubtype[Cat](animals)

animals.exists(_.isInstanceOf[Cat])