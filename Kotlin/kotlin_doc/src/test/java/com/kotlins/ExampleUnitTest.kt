package com.kotlins

import com.kotlins.sample.ExtendFunction
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)

        var sample = ExtendFunction();
        var result = sample.test(1, 2);

        print("result = " + result)
    }
}
