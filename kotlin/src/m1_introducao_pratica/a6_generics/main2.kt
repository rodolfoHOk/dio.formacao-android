package m1_introducao_pratica.a6_generics

fun <E> mutableStackOf(vararg elements: E) = MutableStack(*elements)

fun main() {
    val stack = mutableStackOf(0.62, 3.14, 2.7)
    println(stack)

    val stack2 = mutableStackOf<Int>(1, 2, 3)
    println(stack2)

    val stack3 = mutableStackOf("A", "B", "C")
    println(stack3)
}
