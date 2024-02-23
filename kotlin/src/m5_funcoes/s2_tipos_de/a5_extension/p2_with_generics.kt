package m5_funcoes.s2_tipos_de.a5_extension

fun <T> T?.nullSafeToString() = this?.toString() ?: "ANNULLED"
fun main() {
    println(null.nullSafeToString())
    println("Kotlin".nullSafeToString())
}
