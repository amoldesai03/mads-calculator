package `in`.android.mads_calculator

import `in`.android.mads_calculator.utils.Calculator
import org.junit.Assert
import org.junit.Test

class MadsCalculatorUnitTest {

    @Test
    fun evaluate() {
        /*
        50 + 20 / 10 = 7
        ○ you first add 50 and 20, which is 70 and then divide by 10 which is 7
        ○ Because in MADS, A takes precedence over D
        */
        val expression = " 50 + 20 / 10 "
        val expectedResult = 7
        //Act
        val result = Calculator.evaluate(expression)
        //Assert
        Assert.assertEquals(expectedResult.toFloat(), result)
    }

    @Test
    fun evaluate2() {
        /*
        ● 50 / 20 + 5 = 2
        ○ You first add 20 and 5, which is 25, and then divide 50 by 25 which is 2
        ○ Because in MADS, A takes precedence over D
        */
        val expression = " 50 / 20 + 5 "
        val expectedResult = 2
        //Act
        val result = Calculator.evaluate(expression)
        //Assert
        Assert.assertEquals(expectedResult.toFloat(), result)
    }

    @Test
    fun evaluate3() {
        /*
        ● 25 - 2 * 10 = 5
        ○ You first multiply 2 by 10 which is 20, and then subtract 20 from 25
        which is 5 ○ Because in MADS, M takes precedence over S
        */
        val expression = " 25 - 2 * 10 "
        val expectedResult = 5
        //Act
        val result = Calculator.evaluate(expression)
        //Assert
        Assert.assertEquals(expectedResult.toFloat(), result)
    }

    @Test
    fun evaluate4() {
        /*
        ● 10 / 2 - 20 = -15
        ○ You first divide 10 by 2 which is 5 and then subtract 20 from 5 whichis -15
        ○ Because in MADS, D takes precedence over S
        */
        val expression = " 10 / 2 - 20 "
        val expectedResult = -15
        //Act
        val result = Calculator.evaluate(expression)
        //Assert
        Assert.assertEquals(expectedResult.toFloat(), result)
    }

    @Test
    fun evaluate5() {
        /*
        10 - 2 - 3 = 5
        ○ Since there are 2 ‘-’ operators, you choose the leftmost operation first,
        hence you subtract 2 from 10 which is 8, and then subtract 3 from 8,
        which is 5
        */
        val expression = " 10 - 2 - 3 "
        val expectedResult = 5
        //Act
        val result = Calculator.evaluate(expression)
        //Assert
        Assert.assertEquals(expectedResult.toFloat(), result)
    }

    @Test
    fun evaluate6() {
        /*
        ● 10 / 2 / 5 = 1
        ○ Divide 10 by 2, which is 5, and then divide the result by 5, which is 1.
        Because you start with the leftmost operation similar to the above
        */
        val expression = " 10 / 2 / 5 "
        val expectedResult = 1
        //Act
        val result = Calculator.evaluate(expression)
        //Assert
        Assert.assertEquals(expectedResult.toFloat(), result)
    }

    @Test
    fun evaluate7() {
        /*
        ● 10 / 2 / 4 + 1 = 1
        ○ You first add 4 and 1, which is 5. Because in MADS, A takes precedence over D.
        ○ Then divide 10 by 2, which is 5, and then divide it by 5 (from the previous 4 + 1 result). Because you start with the leftmost operation similar
        to the above example.
        */
        val expression = " 10 / 2 / 4 + 1"
        val expectedResult = 1
        //Act
        val result = Calculator.evaluate(expression)
        //Assert
        Assert.assertEquals(expectedResult.toFloat(), result)
    }
}