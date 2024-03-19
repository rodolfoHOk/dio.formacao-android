package me.dio.android.minhasreceitas.data.entity

import androidx.room.Embedded
import androidx.room.Relation

data class FullRecipe(
    @Embedded val recipe: Recipe,
    @Relation(
        parentColumn = "id",
        entityColumn = "recipeId"
    )
    var ingredients: List<Ingredient>,
    @Relation(
        parentColumn = "id",
        entityColumn = "recipeId"
    )
    var prepareModes: List<PrepareMode>
)
