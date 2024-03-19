package me.dio.android.minhasreceitas.domain.usecase

import me.dio.android.minhasreceitas.domain.model.IngredientDomain
import me.dio.android.minhasreceitas.domain.repository.RecipeRepository

class InsertIngredientsUseCase(
    private val repository: RecipeRepository
) {
    suspend operator fun invoke(ingredientDomain: IngredientDomain) =
        repository.insertIngredient(ingredientDomain)
}
