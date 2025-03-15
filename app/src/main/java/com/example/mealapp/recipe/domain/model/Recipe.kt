package com.example.mealapp.recipe.domain.model

data class Recipe(
    val id: String,
    val name: String,
    val thumbnail: String,
    val instructions: String,
    val category: String = "",
    val area: String = "",
    val tags: List<String> = emptyList(),
    val youtubeUrl: String? = null,
    val ingredients: List<Ingredient> = emptyList()
)

data class Ingredient(
    val name: String,
    val measure: String
)