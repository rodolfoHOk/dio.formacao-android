package m4_poo.a3_enum_classes

enum class State {
    IDLE, RUNNING, FINISHED
}

fun main() {
    val state = State.RUNNING
//    val state = State.IDLE
//    val state = State.FINISHED

    val message = when (state) {
        State.IDLE -> "It's idle"
        State.RUNNING -> "It's running"
        State.FINISHED -> "It's finished"
    }
    println(message)
}
