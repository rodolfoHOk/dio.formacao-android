package m5_funcoes.s1_de_escopo.a3_with

class Configuration(val host: String, val port: Int)

fun main() {
    val configuration = Configuration(host = "127.0.0.1", port = 9000)

    with(configuration) {
        println("$host:$port")
    }

    // instead of:
    println("${configuration.host}:${configuration.port}")

    // alternative
    configuration.run {
        println("$host:$port")
    }
}
