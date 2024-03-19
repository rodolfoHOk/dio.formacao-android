package me.dio.android.minhasreceitas.domain.model

typealias FullRecipeDomain = FullRecipe

data class FullRecipe(
    val recipe: Recipe,
    var ingredients: List<Ingredient>,
    var prepareMode: List<PrepareMode>
)
