package me.dio.android.minhasreceitas.domain.usecase

import me.dio.android.minhasreceitas.domain.repository.RecipeRepository

class GetRecipeWithIngredientsAndPrepareModeUseCase(
    private val repository: RecipeRepository
) {
    suspend operator fun invoke(idRecipe: Int) =
        repository.getRecipeWithIngredientsAndPrepareMode(idRecipe)
}
