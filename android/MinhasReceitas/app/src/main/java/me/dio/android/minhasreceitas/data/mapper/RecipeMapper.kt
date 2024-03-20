package me.dio.android.minhasreceitas.data.mapper

import me.dio.android.minhasreceitas.data.entity.RecipeEntity
import me.dio.android.minhasreceitas.domain.model.RecipeDomain

fun RecipeDomain.toEntity() = RecipeEntity (
    id = id,
    name = name,
    prepareTime = prepareTime
)

fun RecipeEntity.toDomain() = RecipeDomain (
    id = id,
    name = name,
    prepareTime = prepareTime
)
