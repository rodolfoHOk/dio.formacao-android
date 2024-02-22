package m4_poo.a1_heranca

open class Dog {
    open fun sayHello() {
        println("wow wow!")
    }
}

class Yorkshire : Dog() {
    override fun sayHello() {
        println("wif wif!")
    }
}

fun main() {
    val dog: Dog = Yorkshire()
    dog.sayHello()
}
