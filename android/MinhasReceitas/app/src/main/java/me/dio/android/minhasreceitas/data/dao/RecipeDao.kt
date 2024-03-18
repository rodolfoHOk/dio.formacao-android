package me.dio.android.minhasreceitas.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import me.dio.android.minhasreceitas.data.entity.RecipeEntity

@Dao
interface RecipeDao {
    @Query("SELECT * FROM recipe")
    fun getAll() : List<RecipeEntity>

    @Insert
    fun insert(recipe: RecipeEntity)
}
