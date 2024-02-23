package m5_funcoes.s2_tipos_de.a6_suspend

/**
 * Exemplo básico de uma [suspend] function com Coroutines.
 *
 * @see [Coroutines basics](https://kotlinlang.org/docs/coroutines-basics.html)
 */
import kotlinx.coroutines.*

fun main() = runBlocking {
    doWorld()
}

suspend fun doWorld() = coroutineScope {
    launch {
        delay(1000L)
        println("World!")
    }
    print("Hello ")
}
