package m3_colecoes.a4_functions

fun main() {
    val fruitsBag = listOf("apple","orange","banana","grapes")
    val clothesBag = listOf("shirts","pants","jeans")
    val cart = listOf(fruitsBag, clothesBag)
    val mapBagSize = cart.map { it.size }
    val mapBag = cart.map { it }
    val flatMapBag = cart.flatMap { it }

    println("Your cart has bags with: $mapBagSize elements")
    println("Your bags are: $mapBag")
    println("The things you bought are: $flatMapBag")
}
