package me.dio.android.minhasreceitas.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

typealias PrepareModeEntity = PrepareMode

@Entity
data class PrepareMode (
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "recipeId") val recipeId: Int,
)
