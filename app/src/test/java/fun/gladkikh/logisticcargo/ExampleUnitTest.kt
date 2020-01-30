package `fun`.gladkikh.logisticcargo

import com.google.gson.GsonBuilder
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)

        val gson = GsonBuilder().create()
        val data = object {
            val command = "login"
            val password = "111"
        }

        println(gson.toJson(data))
    }
}
