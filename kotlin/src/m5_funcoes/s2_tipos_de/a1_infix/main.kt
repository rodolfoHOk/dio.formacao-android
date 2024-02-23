package m5_funcoes.s2_tipos_de.a1_infix

class Person(val name: String) {
    val likedPeople = mutableListOf<Person>()
    infix fun likes(other: Person) { likedPeople.add(other) }
}

fun main() {
    infix fun Int.times(str: String) = str.repeat(this)
    println(3 times "Bye ")

    val pair = "Ferrari" to "Katrina"
    println(pair)

    infix fun String.onto(other: String) = Pair(this, other)
    val myPair = "McLaren" onto "Lucas"
    println(myPair)

    val sophia = Person("Sophia")
    val claudia = Person("Claudia")
    sophia likes claudia
    sophia.likedPeople.forEach { people ->
        println("Sophia like: ${people.name}")
    }
    claudia.likedPeople.forEach { people ->
        println("Claudia like: ${people.name}")
    } // print nothing
}
