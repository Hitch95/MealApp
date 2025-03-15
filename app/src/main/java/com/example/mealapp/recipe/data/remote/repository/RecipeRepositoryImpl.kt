package com.example.mealapp.recipe.data.remote.repository

import com.example.mealapp.recipe.data.remote.service.MealApiService
import com.example.mealapp.recipe.domain.model.Recipe
import com.example.mealapp.recipe.domain.repository.RecipeRepository

class RecipeRepositoryImpl(private val apiService: MealApiService) : RecipeRepository {
    override suspend fun getRecipesByFirstLetter(letter: Char): List<Recipe> {
        return apiService.searchMealsByFirstLetter(letter).meals?.map { it.toRecipe() } ?: emptyList()
    }

    override suspend fun getRecipeDetails(id: String): Recipe? {
        try {
            val response = apiService.getMealById(id)
            return response.meals?.firstOrNull()?.toRecipe()
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }
    }
}