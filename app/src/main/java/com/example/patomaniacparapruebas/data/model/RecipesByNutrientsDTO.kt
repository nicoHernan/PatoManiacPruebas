package com.example.patomaniacparapruebas.data.model

import com.google.gson.annotations.SerializedName

class RecipesByNutrientsDTO(

    @SerializedName("idRecipesByNutrients")
    val id: Int,
    @SerializedName("titleRecipesByNutrients")
    val title: String,
    @SerializedName("imageRecipesByNutrients")
    val image: String,
    val imageType: String,
    @SerializedName("caloriesRecipesByNutrients")
    val calories: Int,
    @SerializedName("proteinRecipesByNutrients")
    val protein: String,
    val fat: String,
    @SerializedName("carbsRecipesByNutrients")
    val carbs: String
)
