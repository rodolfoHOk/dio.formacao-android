package m1_introducao_pratica.a6_generics

class MutableStack<E>(vararg items: E) {

    private val elements = items.toMutableList()

    fun push(element: E) = elements.add(element)

    fun peek(): E = elements.last()

    fun pop(): E = elements.removeAt(elements.size - 1)

    fun isEmpty() = elements.isEmpty()

    fun size() = elements.size

    override fun toString() = "MutableStack(${elements.joinToString()})"
}

fun main() {
    val stack = MutableStack(0.62, 3.14, 2.7)
    println(stack)
    stack.push(9.87)
    println(stack)

    println(stack.isEmpty())

    println("peek(): ${stack.peek()}")
    println(stack)

    for (i in 1..stack.size()) {
        println("pop(): ${stack.pop()}")
        println(stack)
    }

    val stack2 = MutableStack("A", "B", "C")
    stack2.push("D")
    println(stack2)
    for (i in 1..stack2.size()) {
        println("pop(): ${stack2.pop()}")
        println(stack2)
    }
}
