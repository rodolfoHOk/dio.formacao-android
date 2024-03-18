package me.dio.android.minhasreceitas.domain.usecase

import me.dio.android.minhasreceitas.domain.model.RecipeDomain
import me.dio.android.minhasreceitas.domain.repository.RecipeRepository

class InsertRecipeUseCase (
    private val repository: RecipeRepository
) {
    suspend operator fun invoke(recipe: RecipeDomain) = repository.insert(recipe)
}
