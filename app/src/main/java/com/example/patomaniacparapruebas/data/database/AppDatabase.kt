package com.example.patomaniacparapruebas.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [AthletesEntity::class, NutritionEntity::class],
    version = 1
)

abstract class AppDatabase: RoomDatabase() {
    abstract fun athletesDAO(): AppDAO
}