package m1_introducao_pratica.a3_variaveis

fun someCondition() = false // false or true

fun main() {
    val d: Int
    if (someCondition()) {
        d = 1
    } else {
        d = 2
    }
    println(d)
}
