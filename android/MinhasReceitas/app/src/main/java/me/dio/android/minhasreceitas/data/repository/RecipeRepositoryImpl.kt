package me.dio.android.minhasreceitas.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import me.dio.android.minhasreceitas.data.dao.RecipeDao
import me.dio.android.minhasreceitas.data.mapper.toDomain
import me.dio.android.minhasreceitas.data.mapper.toEntity
import me.dio.android.minhasreceitas.domain.model.RecipeDomain
import me.dio.android.minhasreceitas.domain.repository.RecipeRepository

class RecipeRepositoryImpl (
    private val dao: RecipeDao
) : RecipeRepository {
    override suspend fun getAll(): List<RecipeDomain> = withContext(Dispatchers.IO) {
        dao.getAll().map {
            it.toDomain()
        }
    }

    override suspend fun insert(recipe: RecipeDomain) = withContext(Dispatchers.IO) {
        dao.insert(recipe.toEntity())
    }
}
