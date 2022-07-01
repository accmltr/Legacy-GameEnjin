import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.wordspec.AnyWordSpec

object testingTesting

class CalculatorSuite extends AnyFunSuite{
   val calc = new Calculator

   test("Multiply with 0 should always == 0") {
     assert(calc.multiply(7,0) == 0)
     assert(calc.multiply(-7,0) == 0)
     assert(calc.multiply(0,0) == 0)
   }

  test("Devision of x by 1 always gives x") {
    var x = 5
    assert(calc.devide(x,1) == x)
    assert(calc.devide(-x,1) == -x)
    assert(calc.devide(x*2,1) == x*2)
  }

  test("Devision by 0 should give Arithmetic Exception") {
    assertThrows[ArithmeticException](calc.devide(5,0))
  }
}

class CalculatorWordSpec extends AnyWordSpec {
  val calc = new Calculator

  "A calculator" should {
    "return 0 if multiplying any number by 0" in {
      assert(calc.multiply(7,0) == 1)
      assert(calc.multiply(-7,0) == 0)
      assert(calc.multiply(0,0) == 0)
    }
    "and it" should {
      "give back any number without change, if deviding by 1" in {
        val x = 5
        assert(calc.devide(x,1) == x)
        assert(calc.devide(-x,1) == -x)
        assert(calc.devide(x*2,1) == x*2)
      }
    }
    "give an ArithmeticException when dividing by 0" in {
      assertThrows[ArithmeticException](calc.devide(23234,0))
    }
  }
}

class Calculator {
  def add(x:Int, y: Int): Int = x+y
  def subtract(x:Int, y: Int): Int = x-y
  def devide(x:Int, y: Int): Int =  x/y
  def multiply(x:Int, y: Int): Int = x*y
}