import scala.reflect.ClassTag

trait Animal
case class Dog() extends Animal
case class Cat() extends Animal

val animals: List[Animal] = List(Dog(), Dog())

def containsAnimalSubtype[T : ClassTag](implicit cls: ClassTag[T]) =
  animals.exists(cls.runtimeClass.isInstance(_))

containsAnimalSubtype[Dog]
containsAnimalSubtype[Cat]

animals.exists(_.isInstanceOf[Cat])