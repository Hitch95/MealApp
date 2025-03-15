package com.example.mealapp.recipe.presentation.components

import androidx.compose.foundation.background
import androidx.compose.ui.tooling.preview.Preview
import com.example.mealapp.ui.theme.MealAppTheme
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil3.compose.AsyncImage
import kotlinx.coroutines.flow.collectLatest

import com.example.mealapp.recipe.domain.model.Recipe
import com.example.mealapp.recipe.presentation.viewModel.RecipeListViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeListScreen(
    onRecipeClick: (String) -> Unit = {},
    viewModel: RecipeListViewModel = hiltViewModel()
) {
    val recipes = viewModel.recipes
    val isLoading = viewModel.isLoading
    val error = viewModel.error
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is RecipeListViewModel.UIEvent.ShowError -> {
                    snackbarHostState.showSnackbar(message = event.message)
                }
            }
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = {
            TopAppBar(
                title = { Text(text = "Recettes de cuisine") }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            if (isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            } else if (error != null) {
                Text(
                    text = error,
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(16.dp)
                )
            } else {
                LazyColumn(contentPadding = PaddingValues(8.dp)) {
                    items(recipes) { recipe ->
                        RecipeItem(
                            recipe = recipe,
                            onRecipeClick = { onRecipeClick(recipe.id) }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun RecipeItem(
    recipe: Recipe,
    onRecipeClick: () -> Unit,
    isPreview: Boolean = false
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onRecipeClick() },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column {
            if (isPreview) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1.5f)
                        .background(MaterialTheme.colorScheme.primaryContainer),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Image: ${recipe.name}",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                }
            } else {
                AsyncImage(
                    model = recipe.thumbnail,
                    contentDescription = recipe.name,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1.5f)
                )
            }

            Text(
                text = recipe.name,
                style = MaterialTheme.typography.titleMedium,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun RecipeListScreenPreview() {
    // Create mock recipe data for the preview
    val mockRecipes = listOf(
        Recipe(
            id = "52771",
            name = "Spicy Arrabiata Penne",
            thumbnail = "https://www.themealdb.com/images/media/meals/ustsqw1468250014.jpg",
            instructions = "Some instructions here"
        ),
        Recipe(
            id = "52772",
            name = "Teriyaki Chicken Casserole",
            thumbnail = "https://www.themealdb.com/images/media/meals/wvpsxx1468256321.jpg",
            instructions = "More cooking instructions"
        ),
        Recipe(
            id = "52773",
            name = "Beef Stroganoff",
            thumbnail = "https://www.themealdb.com/images/media/meals/svprys1511176755.jpg",
            instructions = "Cook beef and serve with sauce"
        ),
        Recipe(
            id = "52774",
            name = "Chicken Alfredo Primavera",
            thumbnail = "https://www.themealdb.com/images/media/meals/syqypv1486981727.jpg",
            instructions = "Cook pasta and add sauce"
        ),
        Recipe(
            id = "52775",
            name = "Baked Salmon with Fennel",
            thumbnail = "https://www.themealdb.com/images/media/meals/1548772327.jpg",
            instructions = "Bake the fish until done"
        )
    )

    MealAppTheme {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(text = "Recipes") }
                )
            }
        ) { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                LazyColumn(
                    contentPadding = PaddingValues(8.dp)
                ) {
                    items(mockRecipes) { recipe ->
                        RecipeItem(
                            recipe = recipe,
                            onRecipeClick = { }
                        )
                    }
                }
            }
        }
    }
}