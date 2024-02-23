package m7_projeto

// [Template no Kotlin Playground](https://pl.kotl.in/WcteahpyN)

enum class Nivel { BASICO, INTERMEDIARIO, AVANCADO }

data class Usuario(val id: Int, val nome: String)

data class ConteudoEducacional(val nome: String, var nivel: Nivel, val duracao: Int = 60) {
    val inscritos = mutableListOf<Usuario>()

    fun matricular(vararg usuarios: Usuario) {
        inscritos.addAll(usuarios)
    }
}

data class Formacao(val nome: String, var nivel: Nivel, var conteudos: List<ConteudoEducacional>) {
    val inscritos = mutableListOf<Usuario>()

    fun matricular(vararg usuarios: Usuario) {
        inscritos.addAll(usuarios)
        for (conteudo in conteudos) {
            conteudo.matricular(*usuarios)
        }
    }
}

fun main() {
    val introducaoKotlin = ConteudoEducacional("Introducao ao Kotlin", Nivel.BASICO, 60)
    val colecoesKotlin = ConteudoEducacional("Colecoes no Kotlin", Nivel.BASICO, 120)
    val pooKotlin = ConteudoEducacional("POO no Kotlin", Nivel.INTERMEDIARIO, 120)
    val kotlin = Formacao("Kotlin", Nivel.INTERMEDIARIO, listOf(introducaoKotlin, colecoesKotlin, pooKotlin))
    val usuarioA = Usuario(1, "Usuario A")
    kotlin.matricular(usuarioA)
    relatorio(kotlin)

    val introducaoJava = ConteudoEducacional("Introducao ao Java", Nivel.BASICO, 60)
    val colecoesJava = ConteudoEducacional("Colecoes no Java", Nivel.BASICO, 120)
    val pooJava = ConteudoEducacional("POO no Java", Nivel.INTERMEDIARIO, 120)
    val java = Formacao("Java", Nivel.INTERMEDIARIO, listOf(introducaoJava, colecoesJava, pooJava))
    val usuarioB = Usuario(2, "Usuario B")
    val usuarioC = Usuario(3, "Usuario C")
    java.matricular(usuarioB, usuarioC)
    relatorio(java)
}

fun relatorio(formacao: Formacao) {
    println("Formação: ${formacao.nome}")
    println("Nivel: ${formacao.nivel}")
    println("Inscritos: ${formacao.inscritos.size}")
    println("Conteúdos:")
    for (conteudo in formacao.conteudos) {
        println("\t ${conteudo.nome}: nível: ${conteudo.nivel}, inscritos: ${conteudo.inscritos.size}")
    }
    println()
}
