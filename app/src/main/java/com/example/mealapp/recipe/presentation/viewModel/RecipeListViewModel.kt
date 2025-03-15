package com.example.mealapp.recipe.presentation.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mealapp.recipe.domain.getRecipesUseCase.GetRecipesUseCase
import com.example.mealapp.recipe.domain.model.Recipe
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeListViewModel @Inject constructor(
    private val getRecipesUseCase: GetRecipesUseCase
) : ViewModel() {

    var recipes by mutableStateOf<List<Recipe>>(emptyList())
        private set

    var isLoading by mutableStateOf(false)
        private set

    var error by mutableStateOf<String?>(null)
        private set

    private val _eventFlow = MutableSharedFlow<UIEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    init {
        loadRecipes('a')
    }

    fun loadRecipes(letter: Char) {
        viewModelScope.launch {
            isLoading = true
            error = null

            try {
                recipes = getRecipesUseCase(letter)
                isLoading = false
            } catch (e: Exception) {
                recipes = emptyList()
                isLoading = false
                error = e.localizedMessage ?: "an error happened"
                _eventFlow.emit(UIEvent.ShowError(error!!))
            }
        }
    }

    sealed class UIEvent {
        data class ShowError(val message: String) : UIEvent()
    }
}