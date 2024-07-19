package com.example.patomaniacparapruebas.data.network

import com.example.patomaniacparapruebas.data.model.RecipesByNutrientsDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServiceApp {

    @GET("recipes/findByNutrients")
    suspend fun getRecipesByNutrients(
        @Query("apiKey") apiKey: String = "907cc663bcbd49b2a74dcc2010304251",
        @Query("minCarbs") minCarbs: String = "25",
        @Query("maxCarbs") maxCarbs: String = "100"
    ): Response<ArrayList <RecipesByNutrientsDTO> >
}