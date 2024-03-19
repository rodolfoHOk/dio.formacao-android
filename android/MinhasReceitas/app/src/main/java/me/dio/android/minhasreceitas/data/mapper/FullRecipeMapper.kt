package me.dio.android.minhasreceitas.data.mapper

import me.dio.android.minhasreceitas.data.entity.FullRecipeEntity
import me.dio.android.minhasreceitas.domain.model.FullRecipeDomain

fun FullRecipeDomain.toEntity() = FullRecipeEntity(
    recipe = recipe.toEntity(),
    ingredients = ingredients.map {
        it.toEntity()
    },
    prepareMode = prepareMode.map {
        it.toEntity()
    }
)

fun FullRecipeEntity.toDomain() = FullRecipeDomain(
    recipe = recipe.toDomain(),
    ingredients = ingredients.map {
        it.toDomain()
    },
    prepareMode = prepareMode.map {
        it.toDomain()
    }
)
