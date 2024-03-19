package me.dio.android.minhasreceitas.presentation.detail.adapter

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import kotlinx.coroutines.launch
import me.dio.android.minhasreceitas.data.db
import me.dio.android.minhasreceitas.data.repository.RecipeRepositoryImpl
import me.dio.android.minhasreceitas.domain.model.IngredientDomain
import me.dio.android.minhasreceitas.domain.model.PrepareModeDomain
import me.dio.android.minhasreceitas.domain.usecase.GetRecipeWithIngredientsAndPrepareModeUseCase
import me.dio.android.minhasreceitas.domain.usecase.InsertIngredientsUseCase
import me.dio.android.minhasreceitas.domain.usecase.InsertPrepareModeUseCase
import me.dio.android.minhasreceitas.presentation.detail.ItemListState
import me.dio.android.minhasreceitas.presentation.mapper.toModelIngredient
import me.dio.android.minhasreceitas.presentation.mapper.toModelPrepareMode

class ItemListViewModel(
    private val getRecipeWithIngredientsAndPrepareModeUseCase: GetRecipeWithIngredientsAndPrepareModeUseCase,
    private val insertIngredientsUseCase: InsertIngredientsUseCase,
    private val insertPrepareModeUseCase: InsertPrepareModeUseCase
) : ViewModel() {
    fun getRecipeWithIngredientsAndPrepareMode(recipeId: Int): LiveData<ItemListState> = liveData {
        emit(ItemListState.Loading)
        val state = try {
            val fullRecipe = getRecipeWithIngredientsAndPrepareModeUseCase(recipeId)
            ItemListState.Success(
                ingredients = fullRecipe.ingredients.toModelIngredient(),
                prepareMode = fullRecipe.prepareMode.toModelPrepareMode()
            )
        } catch (ex: Exception) {
            Log.e("Error", ex.message.toString())
            ItemListState.Error(ex.message.toString())
        }
        emit(state)
    }

    fun insertIngredientsOrPrepareMode(typeInsert: String, name: String, recipeId: Int) =
        viewModelScope.launch {
            if (typeInsert == "INGREDIENT") {
                insertIngredientsUseCase(IngredientDomain(name = name, recipeId = recipeId))
            } else {
                insertPrepareModeUseCase(PrepareModeDomain(description = name, recipeId = recipeId))
            }
        }

    class Factory : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
            val application =
                checkNotNull(extras[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY])
            val repository = RecipeRepositoryImpl(application.db.recipeDao())
            return ItemListViewModel(
                getRecipeWithIngredientsAndPrepareModeUseCase = GetRecipeWithIngredientsAndPrepareModeUseCase(repository),
                insertIngredientsUseCase = InsertIngredientsUseCase(repository),
                insertPrepareModeUseCase = InsertPrepareModeUseCase(repository)
            ) as T
        }
    }
}
