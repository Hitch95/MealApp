package com.example.mealapp.di

import com.example.mealapp.recipe.data.remote.service.MealApiService
import com.example.mealapp.recipe.data.remote.repository.RecipeRepositoryImpl
import com.example.mealapp.recipe.domain.repository.RecipeRepository

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()
    }

    @Provides
    @Singleton
    fun provideMealApiService(client: OkHttpClient): MealApiService {
        return Retrofit.Builder()
            .baseUrl("https://www.themealdb.com/api/json/v1/1/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(MealApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideRecipeRepository(apiService: MealApiService): RecipeRepository {
        return RecipeRepositoryImpl(apiService)
    }
}