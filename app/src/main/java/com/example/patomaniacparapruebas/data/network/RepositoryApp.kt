package com.example.patomaniacparapruebas.data.network

import com.example.patomaniacparapruebas.data.database.AppDAO
import com.example.patomaniacparapruebas.data.database.AthletesEntity
import com.example.patomaniacparapruebas.data.database.NutritionEntity
import com.example.patomaniacparapruebas.data.model.RecipesByNutrientsDTO
import com.example.patomaniacparapruebas.features.athlete.AthleteDetails
import javax.inject.Inject

class RepositoryApp @Inject constructor(
    private val apiServiceApp: ApiServiceApp,
    private val appDAO: AppDAO
) {

    suspend fun getRecipesByNutrientsDTOResponse(): ArrayList<RecipesByNutrientsDTO>? {
        return apiServiceApp.getRecipesByNutrients().body()
    }
    suspend fun getAthletes(): ArrayList<AthleteDetails> {
        return arrayListOf()                                //no es necesario que las funciones de la db sean suspend
    }


    //DB Nutrients

    fun getAllRecipesByNutrientsDBLocal(): List<NutritionEntity> = appDAO.getAllNutrition()

    fun insertAllRecipesByNutrientsDBLocal(listRecipesByNutrientsDTO: List<NutritionEntity> ) {
        appDAO.insertAllNutrition(listRecipesByNutrientsDTO)
    }


    //DB Athletes

    fun getAllAthletesDBLocal(): List<AthletesEntity> = appDAO.getAllAthletes()

    fun insertAllAthletesDBLocal(listOfAthletes: List<AthletesEntity>) {
        appDAO.insertAllAthletes(listOfAthletes)
    }

    fun insertAthleteDBLocal(athleteInfo: AthletesEntity) {
        appDAO.insertAthletes(athleteInfo)
    }
}