package m4_poo.a4_sealed_classes

sealed class Mammal(val name: String)

class Cat(val catName: String) : Mammal(catName)
class Human(val humanName: String, val job: String) : Mammal(humanName)

fun greetMammal(mammal: Mammal): String {
    return when (mammal) {
        is Human -> "Hello ${mammal.name}; You're working as a ${mammal.job}"
        is Cat -> "Hello ${mammal.name}"
    }
}

fun main() {
    val catSnowy = Cat("Snowy")
    println(greetMammal(catSnowy))

    val humanBob = Human("Bob", "Software Engineer")
    println(greetMammal(humanBob))

    println(catSnowy.name)
    println(catSnowy.catName)

    println(humanBob.name)
    println(humanBob.humanName)
    println(humanBob.job)
}
