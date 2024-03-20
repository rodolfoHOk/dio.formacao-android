package me.dio.android.minhasreceitas.domain.repository

import kotlinx.coroutines.flow.Flow
import me.dio.android.minhasreceitas.domain.model.FullRecipeDomain
import me.dio.android.minhasreceitas.domain.model.IngredientDomain
import me.dio.android.minhasreceitas.domain.model.PrepareModeDomain
import me.dio.android.minhasreceitas.domain.model.RecipeDomain

interface RecipeRepository {
    suspend fun getAll() : Flow<List<RecipeDomain>>

    suspend fun insert(recipe: RecipeDomain) : Unit

    suspend fun insertIngredient(ingredient: IngredientDomain) : Unit

    suspend fun insertPrepareMode(prepareMode: PrepareModeDomain) : Unit

    suspend fun getRecipeWithIngredientsAndPrepareMode(idRecipe: Int): FullRecipeDomain

    suspend fun updateIngredient(ingredient: IngredientDomain) : Unit

    suspend fun updatePrepareMode(prepareMode: PrepareModeDomain) : Unit

    suspend fun deleteIngredient(ingredient: IngredientDomain) : Unit

    suspend fun deletePrepareMode(prepareMode: PrepareModeDomain) : Unit
}
