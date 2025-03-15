package com.example.mealapp.recipe.data.remote.service

import com.example.mealapp.recipe.data.remote.dto.MealDto
import com.example.mealapp.recipe.data.remote.dto.MealsResponse

import retrofit2.http.GET
import retrofit2.http.Query

interface MealApiService {
    @GET("search.php")
    suspend fun searchMealsByFirstLetter(@Query("f") letter: Char): MealsResponse

    @GET("lookup.php")
    suspend fun getMealById(@Query("i") id: String): MealsResponse
}