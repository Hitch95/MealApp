package com.example.mealapp.di

import android.app.Application
import android.util.Log
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MealApplication : Application() {
    override fun onCreate() {
        try {
            super.onCreate()
        } catch (e: Exception) {
            Log.e("MealApplication", "Failed to initialize application", e)
        }
    }
}