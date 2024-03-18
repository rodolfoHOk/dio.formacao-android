package me.dio.android.minhasreceitas.domain.usecase

import me.dio.android.minhasreceitas.domain.repository.RecipeRepository

class GetAllRecipesUseCase (
    private val repository: RecipeRepository
) {
    suspend operator fun invoke() = repository.getAll()
}
