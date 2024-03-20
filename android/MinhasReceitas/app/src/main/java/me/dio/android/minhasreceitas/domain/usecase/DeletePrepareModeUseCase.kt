package me.dio.android.minhasreceitas.domain.usecase

import me.dio.android.minhasreceitas.domain.model.PrepareModeDomain
import me.dio.android.minhasreceitas.domain.repository.RecipeRepository

class DeletePrepareModeUseCase(
    private val repository: RecipeRepository
) {
    suspend operator fun invoke(prepareModeDomain: PrepareModeDomain) {
        repository.deletePrepareMode(prepareModeDomain)
    }
}
