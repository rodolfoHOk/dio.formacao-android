package me.dio.android.minhasreceitas.domain.usecase

import me.dio.android.minhasreceitas.domain.model.PrepareModeDomain
import me.dio.android.minhasreceitas.domain.repository.RecipeRepository

class InsertPrepareModeUseCase(
    private val repository: RecipeRepository
) {
    suspend operator fun invoke(prepareMode: PrepareModeDomain) =
        repository.insertPrepareMode(prepareMode)
}
