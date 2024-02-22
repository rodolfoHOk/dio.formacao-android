package m3_colecoes.a2_set

val openIssues: MutableSet<String> = mutableSetOf("uniqueDescription1", "uniqueDescription2", "uniqueDescription3")

fun addIssue(uniqueDescription: String): Boolean {
    return openIssues.add(uniqueDescription)
}

fun getStatusLog(isAdded: Boolean): String {
    return if (isAdded) "registered correctly." else "marked as duplicate and rejected."
}

//fun getStatusLog(isAdded: Boolean) = if (isAdded) "registered correctly." else "marked as duplicate and rejected." // idem

fun main() {
    val aNewIssue: String = "uniqueDescription4"
    val anIssueAlreadyIn: String = "uniqueDescription2"

    println("Issue $aNewIssue ${getStatusLog(addIssue(aNewIssue))}")
    println("Issue $anIssueAlreadyIn ${getStatusLog(addIssue(anIssueAlreadyIn))}")
}
