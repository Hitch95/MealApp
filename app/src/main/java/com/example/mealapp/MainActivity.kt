package com.example.mealapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.mealapp.recipe.presentation.components.MealAppNavigation
import com.example.mealapp.ui.theme.MealAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        try {
            setContent {
                MealAppTheme {
                    Surface(
                        modifier = Modifier.Companion.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        MealAppNavigation()
                    }
                }
            }
        } catch (e: Exception) {
            Log.e("MainActivity", "Error during app initialization", e)
            throw e
        }
    }
}