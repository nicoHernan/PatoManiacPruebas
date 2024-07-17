package com.example.patomaniacparapruebas.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.patomaniacparapruebas.features.athlete.AthleteDetails

@Dao
interface AppDAO {

    @Query("SELECT * FROM athletes")
    fun getAllAthletes(): List<AthletesEntity>  //Todo -> podrían ser ArrayList ?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllAthletes(listAthletes: List<AthletesEntity>)


    @Query("SELECT * FROM nutrition")
    fun getAllNutrition(): List<NutritionEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllNutrition(listNutrition: List<NutritionEntity>)
}