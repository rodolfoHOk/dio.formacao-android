package m2_controle_de_fluxo.a5_expressao_condicional

fun main() {
    fun maxWithoutConditionalExpression(a: Int, b: Int): Int {
        if (a > b) {
            return a
        } else {
            return b
        }
    }

    println(maxWithoutConditionalExpression(99, -42))
    println(maxWithoutConditionalExpression(21, 43))
    println()

    fun max(a: Int, b: Int) = if (a > b) a else b

    println(max(99, -42))
    println(max(21, 43))
}
