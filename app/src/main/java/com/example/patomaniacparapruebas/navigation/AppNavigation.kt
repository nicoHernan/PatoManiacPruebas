package com.example.patomaniacparapruebas.navigation

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.patomaniacparapruebas.R
import com.example.patomaniacparapruebas.features.athlete.AthleteDetails
import com.example.patomaniacparapruebas.features.athlete.AthleteScreen
import com.example.patomaniacparapruebas.features.home.HomeScreen
import com.example.patomaniacparapruebas.features.nutrition.NutritionScreen

enum class NavigationScreen (@StringRes val title:Int){
    Home(title = R.string.home_screen),
    Athlete(title = R.string.athlete_screen ),
    Nutrition(title = R.string.nutrition_screen)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationAppBar(
    modifier: Modifier = Modifier,
    currentScreen: NavigationScreen,
    navigateBack: Boolean,
    navigateUp: () -> Unit
){
    TopAppBar(
        modifier = modifier,
        title = { Text(stringResource(currentScreen.title) )},
        navigationIcon = {
            if(navigateBack){
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.button_back)
                    )
                }
            }
        }
    )
}


@Composable
fun AppNavigation(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    val backStackEntry by navController.currentBackStackEntryAsState()  //referencia a la pila de actividades

    val currentScreen = NavigationScreen.valueOf(
        backStackEntry?.destination?.route ?: NavigationScreen.Home.name
    )
    Scaffold(
        topBar = {
            NavigationAppBar(
                currentScreen = currentScreen,
                navigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() }
            )
        }
    ) { innerPadding ->
        NavHost(
            modifier = Modifier
                .padding(innerPadding),
            navController = navController,
            startDestination = NavigationScreen.Home.name
        )
        {
            composable(route = NavigationScreen.Home.name) {
                HomeScreen(
                    modifier = modifier,
                    navController = navController,
                    goToAthleteDetail = {
                        navController.navigate(NavigationScreen.Athlete.name)
                    }
                )
            }
            composable(route = NavigationScreen.Athlete.name) {
                AthleteScreen(
                    modifier = modifier,
                    navController = navController,
                    enabled = true,
                    onNavigate = {
                        navController.navigate(NavigationScreen.Nutrition.name)
                    }
                )
            }
            composable(route = NavigationScreen.Nutrition.name) { backStackEntry ->

                NutritionScreen(
                    modifier = Modifier,
                    navController = navController,
                    nutritionViewModel = hiltViewModel(),
                    id = "1"
                )
            }
        }
    }
}