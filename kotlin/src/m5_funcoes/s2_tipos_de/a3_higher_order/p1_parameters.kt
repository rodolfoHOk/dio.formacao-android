package m5_funcoes.s2_tipos_de.a3_higher_order

fun calculate(x: Int, y: Int, operation: (Int, Int) -> Int): Int {
    return operation(x, y)
}

fun sum(x: Int, y: Int) = x + y

fun module(x: Int, y: Int) = x % y

fun main() {
    val sumResult = calculate(4, 5, ::sum)
    val mulResult = calculate(4, 5) { a, b -> a * b }
    println("sumResult $sumResult, mulResult $mulResult")

    val moduleResult = calculate(7, 5, ::module)
    println("moduleResult $moduleResult")
    val moduleResult2 = calculate(6, 2, ::module)
    println("moduleResult $moduleResult2")
    val moduleResult3 = calculate(5, 2, ::module)
    println("moduleResult $moduleResult3")
}