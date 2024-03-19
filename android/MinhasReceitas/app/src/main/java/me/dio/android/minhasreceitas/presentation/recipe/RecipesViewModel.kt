package me.dio.android.minhasreceitas.presentation.recipe

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
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
    val state : LiveData<RecipeState> = liveData {
        emit(RecipeState.Loading)
        val state = try {
            val recipes = getAllRecipesUseCase()
            if (recipes.isEmpty()) {
                RecipeState.Empty
            } else {
                RecipeState.Success(recipes)
            }
        } catch (ex: Exception) {
            Log.e("Error", ex.message.toString())
            RecipeState.Failed(ex.message.toString())
        }
        emit(state)
    }

    fun insert(name: String) = viewModelScope.launch {
        insertRecipeUseCase(RecipeDomain(name = name))
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
