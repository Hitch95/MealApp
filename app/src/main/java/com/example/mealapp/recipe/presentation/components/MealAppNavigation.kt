package com.example.mealapp.recipe.presentation.components

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.tooling.preview.Preview
import com.example.mealapp.ui.theme.MealAppTheme
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.ui.Modifier

import com.example.mealapp.recipe.domain.model.Recipe
import com.example.mealapp.recipe.presentation.viewModel.RecipeListViewModel

@Composable
fun MealAppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "recipe_list") {
        composable("recipe_list") {
            val viewModel: RecipeListViewModel = hiltViewModel()
            RecipeListScreen(
                viewModel = viewModel,
                onRecipeClick = { recipeId ->
                    navController.navigate("recipe_detail/$recipeId")
                }
            )
        }

        composable(
            route = "recipe_detail/{recipeId}",
            arguments = listOf(
                navArgument("recipeId") {
                    type = NavType.StringType
                }
            )
        ) {
            RecipeDetailScreen(
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}

//@Preview(showBackground = true, showSystemUi = false)
//@Composable
//fun MealAppNavigationPreview() {
//    MealAppTheme {
//        Row(modifier = Modifier.fillMaxWidth()) {
//            // Mock RecipeListScreen (simplified version)
//            Box(modifier = Modifier
//                .weight(1f)
//                .padding(4.dp)) {
//                val mockRecipes = listOf(
//                    Recipe(
//                        id = "52771",
//                        name = "Spicy Arrabiata Penne",
//                        thumbnail = "https://www.themealdb.com/images/media/meals/ustsqw1468250014.jpg",
//                        instructions = "Some instructions"
//                    ),
//                    Recipe(
//                        id = "52772",
//                        name = "Teriyaki Chicken",
//                        thumbnail = "https://www.themealdb.com/images/media/meals/wvpsxx1468256321.jpg",
//                        instructions = "More instructions"
//                    )
//                )
//                LazyColumn(contentPadding = PaddingValues(8.dp)) {
//                    items(mockRecipes) { recipe ->
//                        RecipeItem(recipe = recipe, onRecipeClick = {})
//                    }
//                }
//            }

//            Spacer(modifier = Modifier.width(8.dp))

            // Mock RecipeDetailScreen (simplified version)
//            Box(modifier = Modifier
//                .weight(1f)
//                .padding(4.dp)) {
//                Column(
//                    modifier = Modifier.fillMaxWidth().padding(16.dp),
//                    horizontalAlignment = Alignment.CenterHorizontally
//                ) {
//                    Text(
//                        text = "Spicy Arrabiata Penne",
//                        style = MaterialTheme.typography.headlineMedium,
//                        modifier = Modifier.padding(bottom = 16.dp)
//                    )
//                    Text(
//                        text = "Recipe details would appear here...",
//                        style = MaterialTheme.typography.bodyMedium
//                    )
//                }
//            }
//        }
//    }
//}