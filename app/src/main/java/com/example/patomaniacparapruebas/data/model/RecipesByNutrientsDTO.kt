package com.example.patomaniacparapruebas.data.model

import com.google.gson.annotations.SerializedName

class RecipesByNutrientsDTO(

    @SerializedName("id")
    val idRecipesByNutrients: Int,
    @SerializedName("title")
    val titleRecipesByNutrients: String,
    @SerializedName("image")
    val imageRecipesByNutrients: String,
    val imageType: String,
    @SerializedName("calories")
    val caloriesRecipesByNutrients: Int,
    @SerializedName("protein")
    val proteinRecipesByNutrients: String,
    val fat: String,
    @SerializedName("carbs")
    val carbsRecipesByNutrients: String
)
