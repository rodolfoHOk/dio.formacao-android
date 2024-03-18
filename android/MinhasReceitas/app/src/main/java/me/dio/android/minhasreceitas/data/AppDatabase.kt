package me.dio.android.minhasreceitas.data

import androidx.room.Database
import androidx.room.RoomDatabase
import me.dio.android.minhasreceitas.data.dao.RecipeDao
import me.dio.android.minhasreceitas.data.entity.RecipeEntity

@Database(
    entities = [
        RecipeEntity::class
    ],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun recipeDao() : RecipeDao
}
