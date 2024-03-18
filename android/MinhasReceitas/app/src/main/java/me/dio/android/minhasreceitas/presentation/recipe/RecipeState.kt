package me.dio.android.minhasreceitas.presentation.recipe

import me.dio.android.minhasreceitas.domain.model.RecipeDomain

sealed interface RecipeState {
    object Loading : RecipeState
    object Empty: RecipeState
    data class Success(val recipes: List<RecipeDomain>) : RecipeState
    data class Failed(val message: String) : RecipeState
}
