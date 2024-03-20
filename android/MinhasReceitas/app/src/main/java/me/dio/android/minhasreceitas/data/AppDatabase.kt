package me.dio.android.minhasreceitas.data

import androidx.room.Database
import androidx.room.RoomDatabase
import me.dio.android.minhasreceitas.data.dao.RecipeDao
import me.dio.android.minhasreceitas.data.entity.Ingredient
import me.dio.android.minhasreceitas.data.entity.PrepareMode
import me.dio.android.minhasreceitas.data.entity.Recipe

@Database(
    entities = [
        Recipe::class,
        Ingredient::class,
        PrepareMode::class
    ],
    version = 2
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun recipeDao() : RecipeDao
}
