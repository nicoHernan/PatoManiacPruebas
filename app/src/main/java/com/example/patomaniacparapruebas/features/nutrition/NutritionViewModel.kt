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
                    listResponse?.map { recipesByNutrientsDTO ->     //devuelve una lista una vez transformada la collección original
                        NutritionEntity(
                            recipesByNutrientsDTO.idRecipesByNutrients,
                            recipesByNutrientsDTO.titleRecipesByNutrients,
                            recipesByNutrientsDTO.imageRecipesByNutrients,
                            recipesByNutrientsDTO.imageType,
                            recipesByNutrientsDTO.caloriesRecipesByNutrients,
                            recipesByNutrientsDTO.proteinRecipesByNutrients,
                            recipesByNutrientsDTO.fat,
                            recipesByNutrientsDTO.carbsRecipesByNutrients
                        )}?.let {
                        repositoryApp.insertAllRecipesByNutrientsDBLocal(
                            it
                        )
                    }
                    _nutritionUiState.update {
                        _nutritionUiState.value.copy(listRecipesByNutrientsDTO = listResponse ?: ArrayList())
                    }

                } else {
                    val nutritionEntity = repositoryApp.getAllRecipesByNutrientsDBLocal().map { nutritionEntity ->
                            RecipesByNutrientsDTO(
                                idRecipesByNutrients = nutritionEntity.id,
                                titleRecipesByNutrients = nutritionEntity.title,
                                imageRecipesByNutrients = nutritionEntity.image,
                                imageType = "",
                                caloriesRecipesByNutrients = nutritionEntity.calories,
                                proteinRecipesByNutrients = nutritionEntity.protein,
                                fat = "",
                                carbsRecipesByNutrients = nutritionEntity.carbs
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