package me.dio.android.minhasreceitas.domain.model

typealias PrepareModeDomain = PrepareMode

data class PrepareMode (
    val id: Int = 0,
    val description: String,
    val recipeId: Int,
)
