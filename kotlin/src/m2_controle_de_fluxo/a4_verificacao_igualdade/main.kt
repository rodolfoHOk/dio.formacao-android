package m2_controle_de_fluxo.a4_verificacao_igualdade

fun main() {
    val authors = setOf("Shakespeare", "Hemingway", "Twain")
    val writers = setOf("Twain", "Shakespeare", "Hemingway")
    val writers2 = authors

    println(authors == writers)
    println(authors === writers)
    println(authors === writers2)
}
