package com.example.mealapp.recipe.domain.getRecipesUseCase

import com.example.mealapp.recipe.domain.model.Recipe
import com.example.mealapp.recipe.domain.repository.RecipeRepository
import javax.inject.Inject

class GetRecipesUseCase @Inject constructor(
    private val repository: RecipeRepository
) {
    suspend operator fun invoke(letter: Char = 'a'): List<Recipe> {
        return repository.getRecipesByFirstLetter(letter)
    }
}