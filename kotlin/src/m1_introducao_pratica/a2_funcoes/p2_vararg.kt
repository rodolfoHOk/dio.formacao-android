package m1_introducao_pratica.a2_funcoes

fun main() {
    fun printAll(vararg messages: String) {
        for (m in messages) println(m)
    }
    printAll("Olá", "Hello", "Hallo", "Salut", "Hola", "你好")

    fun printAllWithPrefix(vararg messages: String, prefix: String) {
        for (m in messages) println(prefix + m)
    }
    printAllWithPrefix(
        "Olá", "Hello", "Hallo", "Salut", "Hola", "你好",
        prefix = "Greeting: "
    )

    fun log(vararg entries: String) {
        printAll(*entries)
    }
    log("Olá", "Hello", "Hallo", "Salut", "Hola", "你好")
}
