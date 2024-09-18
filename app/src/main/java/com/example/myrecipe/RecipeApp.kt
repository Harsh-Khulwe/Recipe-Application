package com.example.myrecipe

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun RecipeApp(navController: NavHostController) {
    val rViewModel: RecipeViewModel = viewModel()
    val viewState by rViewModel.categoriesState

    NavHost(navController = navController, startDestination = Screen.RecipeScreen.route) {
        composable (Screen.RecipeScreen.route) {
            RecipeScreen(viewState = viewState, pleaseNavigate = {
                navController.currentBackStackEntry?.savedStateHandle?.set("Yorichi", it)
                navController.navigate(Screen.DetailScreen.route)
            })
        }
        composable(Screen.DetailScreen.route) {
            val category = navController.previousBackStackEntry?.savedStateHandle?.
            get<Category>("Yorichi") ?: Category("", "", "", "")
            
            CategoryDetailsScreen(category = category)
        }
    }
}