package me.dio.android.minhasreceitas.presentation.recipe

import androidx.lifecycle.ViewModel;
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
import me.dio.android.minhasreceitas.domain.model.RecipeDomain
import me.dio.android.minhasreceitas.domain.usecase.GetAllRecipesUseCase;
import me.dio.android.minhasreceitas.domain.usecase.InsertRecipeUseCase;

class RecipesViewModel(
    private val getAllRecipesUseCase : GetAllRecipesUseCase,
    private val insertRecipeUseCase : InsertRecipeUseCase
) : ViewModel() {
    private val _state = MutableSharedFlow<RecipeState>()
    val state: SharedFlow<RecipeState> = _state

    init {
        getAllRecipes()
    }

    private fun getAllRecipes() = viewModelScope.launch {
        getAllRecipesUseCase()
            .flowOn(Dispatchers.Main)
            .onStart {
                _state.emit(RecipeState.Loading)
            }.catch {
                _state.emit(RecipeState.Failed("Error"))
            }.collect { recipes ->
                if (recipes.isEmpty()) {
                    _state.emit(RecipeState.Empty)
                } else {
                    _state.emit(RecipeState.Success(recipes))
                }
            }
    }

    fun insert(name: String) = viewModelScope.launch {
        insertRecipeUseCase(RecipeDomain(name = name, prepareTime = "45 min"))
    }

    class Factory : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
            val application =
                checkNotNull(extras[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY])
            val repository = RecipeRepositoryImpl(application.db.recipeDao())
            return RecipesViewModel(
                getAllRecipesUseCase = GetAllRecipesUseCase(repository),
                insertRecipeUseCase = InsertRecipeUseCase(repository)
            ) as T
        }
    }
}
