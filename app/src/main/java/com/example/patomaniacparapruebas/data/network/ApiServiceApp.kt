package com.example.patomaniacparapruebas.data.network

import com.example.patomaniacparapruebas.data.model.RecipesByNutrientsDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServiceApp {

    @GET("recipes/findByNutrients")
    suspend fun getRecipesByNutrients(
        @Query("apiKey") apiKey: String = "e488a2c27dc24634b4555faf6769998c"
    ): Response<ArrayList <RecipesByNutrientsDTO> >
}