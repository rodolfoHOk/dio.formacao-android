package me.dio.android.minhasreceitas.presentation.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import me.dio.android.minhasreceitas.data.db
import me.dio.android.minhasreceitas.data.repository.RecipeRepositoryImpl
import me.dio.android.minhasreceitas.domain.model.IngredientDomain
import me.dio.android.minhasreceitas.domain.model.PrepareModeDomain
import me.dio.android.minhasreceitas.domain.usecase.DeleteIngredientUseCase
import me.dio.android.minhasreceitas.domain.usecase.DeletePrepareModeUseCase
import me.dio.android.minhasreceitas.domain.usecase.GetRecipeWithIngredientsAndPrepareModeUseCase
import me.dio.android.minhasreceitas.domain.usecase.InsertIngredientUseCase
import me.dio.android.minhasreceitas.domain.usecase.InsertPrepareModeUseCase
import me.dio.android.minhasreceitas.domain.usecase.UpdateIngredientUseCase
import me.dio.android.minhasreceitas.domain.usecase.UpdatePrepareModeUseCase
import me.dio.android.minhasreceitas.presentation.mapper.toModelIngredient
import me.dio.android.minhasreceitas.presentation.mapper.toModelPrepareMode

class ItemListViewModel(
    private val getRecipeWithIngredientsAndPrepareModeUseCase: GetRecipeWithIngredientsAndPrepareModeUseCase,
    private val insertIngredientUseCase: InsertIngredientUseCase,
    private val insertPrepareModeUseCase: InsertPrepareModeUseCase,
    private val updateIngredientUseCase: UpdateIngredientUseCase,
    private val updatePrepareModeUseCase: UpdatePrepareModeUseCase,
    private val deleteIngredientUseCase: DeleteIngredientUseCase,
    private val deletePrepareModeUseCase: DeletePrepareModeUseCase,
    private val recipeId: Int
) : ViewModel() {
    private val _state = MutableSharedFlow<ItemListState>()
    val state : SharedFlow<ItemListState> = _state

    init {
        getRecipeWithIngredientsAndPrepareMode()
    }

    private fun getRecipeWithIngredientsAndPrepareMode() = viewModelScope.launch {
        getRecipeWithIngredientsAndPrepareModeUseCase(recipeId)
            .flowOn(Dispatchers.Main)
            .onStart {
                _state.emit(ItemListState.Loading)
            }.catch {
                _state.emit(ItemListState.Error("Error"))
            }.collect {fullRecipe ->
                _state.emit(ItemListState.Success(
                    ingredients = fullRecipe.ingredients.toModelIngredient(),
                    prepareMode = fullRecipe.prepareMode.toModelPrepareMode()
                ))
            }
    }

    fun insertIngredientsOrPrepareMode(typeInsert: String, name: String, recipeId: Int) =
        viewModelScope.launch {
            if (typeInsert == "INGREDIENT") {
                insertIngredientUseCase(IngredientDomain(name = name, recipeId = recipeId))
            } else {
                insertPrepareModeUseCase(PrepareModeDomain(description = name, recipeId = recipeId))
            }
        }

    fun updateIngredient(id: Int, name: String, recipeId: Int) = viewModelScope.launch {
        updateIngredientUseCase(IngredientDomain(id = id, name = name, recipeId = recipeId))
    }

    fun removeIngredient(id: Int, name: String, recipeId: Int) = viewModelScope.launch {
        deleteIngredientUseCase(IngredientDomain(id = id, name = name, recipeId = recipeId))
    }

    fun updatePrepareMode(id: Int, name: String, recipeId: Int) = viewModelScope.launch {
        updatePrepareModeUseCase(
            PrepareModeDomain(
                id = id,
                description = name,
                recipeId = recipeId
            )
        )
    }

    fun removePrepareMode(id: Int, name: String, recipeId: Int) = viewModelScope.launch {
        deletePrepareModeUseCase(
            PrepareModeDomain(
                id = id,
                description = name,
                recipeId = recipeId
            )
        )
    }

    class Factory(
        private val recipeId: Int
    ) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
            val application =
                checkNotNull(extras[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY])
            val repository = RecipeRepositoryImpl(application.db.recipeDao())
            return ItemListViewModel(
                getRecipeWithIngredientsAndPrepareModeUseCase =
                GetRecipeWithIngredientsAndPrepareModeUseCase(repository),
                insertIngredientUseCase = InsertIngredientUseCase(repository),
                insertPrepareModeUseCase = InsertPrepareModeUseCase(repository),
                updateIngredientUseCase = UpdateIngredientUseCase(repository),
                updatePrepareModeUseCase = UpdatePrepareModeUseCase(repository),
                deleteIngredientUseCase = DeleteIngredientUseCase(repository),
                deletePrepareModeUseCase = DeletePrepareModeUseCase(repository),
                recipeId = recipeId
            ) as T
        }
    }
}
