package me.dio.android.minhasreceitas.domain.repository

import me.dio.android.minhasreceitas.domain.model.RecipeDomain

interface RecipeRepository {
    suspend fun getAll() : List<RecipeDomain>

    suspend fun insert(recipe: RecipeDomain) : Unit
}
