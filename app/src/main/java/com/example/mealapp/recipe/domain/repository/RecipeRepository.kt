package com.example.mealapp.recipe.domain.repository

import com.example.mealapp.recipe.domain.model.Recipe

interface RecipeRepository {
    suspend fun getRecipesByFirstLetter(letter: Char): List<Recipe>
    suspend fun getRecipeDetails(id: String): Recipe?
}