package com.example.patomaniacparapruebas.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface AppDAO {

    @Query("SELECT * FROM athletes")
    fun getAllAthletes(): List<AthletesEntity>          //no es ArrayList porque Room no lo permite

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllAthletes(listAthletes: List<AthletesEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAthletes(athlete: AthletesEntity)

    @Query("SELECT * FROM nutrition")
    fun getAllNutrition(): List<NutritionEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllNutrition(listNutrition: List<NutritionEntity>)

}