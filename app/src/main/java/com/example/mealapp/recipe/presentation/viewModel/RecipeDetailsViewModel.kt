package com.example.mealapp.recipe.presentation.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mealapp.recipe.domain.model.Recipe
import com.example.mealapp.recipe.domain.repository.RecipeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeDetailsViewModel @Inject constructor(
    private val repository: RecipeRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    var recipe by mutableStateOf<Recipe?>(null)
        private set

    var isLoading by mutableStateOf(false)
        private set

    var error by mutableStateOf<String?>(null)
        private set

    private val _eventFlow = MutableSharedFlow<UIEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    init {
        savedStateHandle.get<String>("recipeId")?.let { recipeId ->
            loadRecipeDetails(recipeId)
        }
    }

    private fun loadRecipeDetails(recipeId: String) {
        viewModelScope.launch {
            isLoading = true
            error = null

            try {
                recipe = repository.getRecipeDetails(recipeId)
                if (recipe == null) {
                    error = "The details of the recipes are not availables."
                    _eventFlow.emit(UIEvent.ShowError(error!!))
                }
                isLoading = false
            } catch (e: Exception) {
                recipe = null
                isLoading = false
                error = e.localizedMessage ?: "An error happened"
                _eventFlow.emit(UIEvent.ShowError(error!!))
            }
        }
    }

    sealed class UIEvent {
        data class ShowError(val message: String) : UIEvent()
    }
}