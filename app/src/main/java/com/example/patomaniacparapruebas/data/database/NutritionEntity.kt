package com.example.patomaniacparapruebas.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "nutrition")
data class NutritionEntity(
    @PrimaryKey
    @ColumnInfo(name = "idRecipesByNutrients")
    val id: Int,
    @ColumnInfo(name = "titleRecipesByNutrients")
    val title: String,
    @ColumnInfo(name = "imageRecipesByNutrients")
    val image: String,
    val imageType: String,
    @ColumnInfo(name = "caloriesRecipesByNutrients")
    val calories: Int,
    @ColumnInfo(name = "proteinRecipesByNutrients")
    val protein: String,
    val fat: String,
    @ColumnInfo(name = "carbsRecipesByNutrients")
    val carbs: String
)
