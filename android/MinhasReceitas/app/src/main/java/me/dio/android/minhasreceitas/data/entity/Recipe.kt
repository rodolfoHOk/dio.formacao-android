package me.dio.android.minhasreceitas.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

typealias RecipeEntity = Recipe

@Entity
data class Recipe (
    @PrimaryKey(autoGenerate = true) val id : Int,
    @ColumnInfo(name = "name") val name: String
)
