package me.dio.android.minhasreceitas.domain.model

typealias IngredientDomain = Ingredient

data class Ingredient (
    val id: Int = 0,
    val name: String,
    val recipeId: Int,
)
