package com.example.patomaniacparapruebas.data.network.di

import android.content.Context
import androidx.room.Room
import com.example.patomaniacparapruebas.data.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RoomModule {

    @Singleton
    @Provides
    fun providesRoomDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        "patomaniac_parapruebas_database"
    ).build()


    @Singleton
    @Provides
    fun providesAthletesDAO(database: AppDatabase) = database.athletesDAO()
}