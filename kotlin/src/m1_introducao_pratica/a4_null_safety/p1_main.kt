package m1_introducao_pratica.a4_null_safety

fun main() {
    fun strLength(notNull: String): Int {
        return notNull.length
    }

    fun strLengthNullable(notNull: String?): Int {
        return notNull?.length ?: 0
    }

    var neverNull: String = "This can't be null"
    println(neverNull)
//    neverNull = null // Null can not be a value of a non-null type String
    println(strLength(neverNull))

    var nullable: String? = "You can keep a null here"
    println(nullable)
    println(strLength(nullable as String)) // need as String
    nullable = null
    println(nullable)
//    println(strLength(nullable)) // Type mismatch: inferred type is Nothing? but String was expected
    println(strLengthNullable(nullable))

    var inferredNonNull = "The compiler assumes non-null"
    println(inferredNonNull)
//    inferredNonNull = null // Null can not be a value of a non-null type String
    println(strLength(inferredNonNull))
}
