package m5_funcoes.s1_de_escopo.a2_run

fun main() {
    fun getNullableLength(ns: String?) : Int {
        println("for \"$ns\":")
        return ns?.run {
            println("\tis empty? " + isEmpty())
            println("\tlength = $length")
            length
        } ?: 0
    }

    println(getNullableLength(null))
    println(getNullableLength(""))
    println(getNullableLength("some string with Kotlin"))
}
