package me.dio.android.minhasreceitas.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import me.dio.android.minhasreceitas.data.entity.FullRecipeEntity
import me.dio.android.minhasreceitas.data.entity.Ingredient
import me.dio.android.minhasreceitas.data.entity.PrepareMode
import me.dio.android.minhasreceitas.data.entity.Recipe

@Dao
interface RecipeDao {
    @Query("SELECT * FROM recipe")
    fun getAll() : List<Recipe>

    @Insert
    fun insert(recipe: Recipe)

    @Insert
    fun insert(ingredient: Ingredient)

    @Insert
    fun insert(prepareMode: PrepareMode)

    @Transaction
    @Query("SELECT * FROM recipe WHERE id = :recipeId")
    fun getRecipeWithIngredientsAndPrepareMode(recipeId: Int) : FullRecipeEntity
}
