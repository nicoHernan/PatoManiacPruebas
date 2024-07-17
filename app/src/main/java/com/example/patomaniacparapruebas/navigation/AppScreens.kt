package com.example.patomaniacparapruebas.navigation

sealed class AppScreens(val route: String) {

    object HomeScreen : AppScreens("home_screen")
    object AthleteScreen : AppScreens("athlete_screen")

    object NutritionScreen:AppScreens("nutrition_screen")
    /*fun createRoute(id: String): String {
        return "nutrition_screen/${id}"
    }*/
}