package me.dio.android.minhasreceitas.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import me.dio.android.minhasreceitas.data.dao.RecipeDao
import me.dio.android.minhasreceitas.data.mapper.toDomain
import me.dio.android.minhasreceitas.data.mapper.toEntity
import me.dio.android.minhasreceitas.domain.model.FullRecipeDomain
import me.dio.android.minhasreceitas.domain.model.IngredientDomain
import me.dio.android.minhasreceitas.domain.model.PrepareModeDomain
import me.dio.android.minhasreceitas.domain.model.RecipeDomain
import me.dio.android.minhasreceitas.domain.repository.RecipeRepository

class RecipeRepositoryImpl(
    private val dao: RecipeDao
) : RecipeRepository {
    override suspend fun getAll(): Flow<List<RecipeDomain>> = withContext(Dispatchers.IO) {
        dao.getAll().map { list ->
            list.map {
                it.toDomain()
            }
        }
    }

    override suspend fun insert(recipe: RecipeDomain) = withContext(Dispatchers.IO) {
        dao.insert(recipe.toEntity())
    }

    override suspend fun insertIngredient(ingredient: IngredientDomain) =
        withContext(Dispatchers.IO) {
            dao.insert(ingredient.toEntity())
        }

    override suspend fun insertPrepareMode(prepareMode: PrepareModeDomain) =
        withContext(Dispatchers.IO) {
            dao.insert(prepareMode.toEntity())
        }

    override suspend fun getRecipeWithIngredientsAndPrepareMode(idRecipe: Int): Flow<FullRecipeDomain> =
        withContext(Dispatchers.IO) {
            dao.getRecipeWithIngredientsAndPrepareMode(idRecipe).map { recipe ->
                recipe.toDomain()
            }
        }

    override suspend fun updateIngredient(ingredient: IngredientDomain) =
        withContext(Dispatchers.IO) {
            dao.updateIngredient(ingredient.toEntity())
        }

    override suspend fun updatePrepareMode(prepareMode: PrepareModeDomain) =
        withContext(Dispatchers.IO) {
            dao.updatePrepareMode(prepareMode.toEntity())
        }

    override suspend fun deleteIngredient(ingredient: IngredientDomain) =
        withContext(Dispatchers.IO) {
            dao.deleteIngredient(ingredient.toEntity())
        }

    override suspend fun deletePrepareMode(prepareMode: PrepareModeDomain) =
        withContext(Dispatchers.IO) {
            dao.deletePrepareMode(prepareMode.toEntity())
        }
}
