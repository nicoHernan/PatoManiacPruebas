package com.example.patomaniacparapruebas.features.nutrition

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.patomaniacparapruebas.data.database.NutritionEntity
import com.example.patomaniacparapruebas.data.model.RecipesByNutrientsDTO
import com.example.patomaniacparapruebas.data.network.RepositoryApp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class NutritionViewModel @Inject constructor(
repositoryApp: RepositoryApp
): ViewModel() {

    private val _nutritionUiState = MutableStateFlow(NutritionUiState() )
    val nutritionUiState: StateFlow<NutritionUiState> = _nutritionUiState

    init {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {

                if (repositoryApp.getAllRecipesByNutrientsDBLocal().isEmpty()) {
                    val listResponse = repositoryApp.getRecipesByNutrientsDTOResponse()
                    repositoryApp.insertAllRecipesByNutrientsDBLocal(

                        listResponse?.map { recipesByNutrientsDTO ->     //devuelve una lista una vez transformada la collección original
                            NutritionEntity(
                                recipesByNutrientsDTO.id,
                                recipesByNutrientsDTO.title,
                                recipesByNutrientsDTO.image,
                                recipesByNutrientsDTO.imageType,
                                recipesByNutrientsDTO.calories,
                                recipesByNutrientsDTO.protein,
                                recipesByNutrientsDTO.fat,
                                recipesByNutrientsDTO.carbs
                            )} !!
                    )
                    _nutritionUiState.update {
                        _nutritionUiState.value.copy(listRecipesByNutrientsDTO = listResponse)
                    }

                } else {
                    val nutritionEntity = repositoryApp.getAllRecipesByNutrientsDBLocal().map { nutritionEntity ->
                            RecipesByNutrientsDTO(
                                id = nutritionEntity.id,
                                title = nutritionEntity.title,
                                image = nutritionEntity.image,
                                imageType = "",
                                calories = nutritionEntity.calories,
                                protein = nutritionEntity.protein,
                                fat = "",
                                carbs = nutritionEntity.carbs
                            )
                        } as ArrayList<RecipesByNutrientsDTO>
                    _nutritionUiState.update {
                        _nutritionUiState.value.copy(
                            listRecipesByNutrientsDTO = nutritionEntity
                        )
                    }
                }
            }
        }
    }
}