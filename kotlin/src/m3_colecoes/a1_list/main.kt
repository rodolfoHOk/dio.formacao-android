package m3_colecoes.a1_list

val systemUsers: MutableList<Int> = mutableListOf(1, 2, 3)
val sudoers: List<Int> = systemUsers

fun addSystemUser(newUser: Int) {
    systemUsers.add(newUser)
}

fun getSysSudoers(): List<Int> {
    return sudoers
}

fun main() {
    addSystemUser(4)
    println("Total system users: ${systemUsers.size}") // 4
    println("Total sudoers: ${getSysSudoers().size}") // 4

    getSysSudoers().forEach {
        i -> println("Some useful info on user $i")
    }
//    getSysSudoers().add(5) // error sudoers is read-only
}
