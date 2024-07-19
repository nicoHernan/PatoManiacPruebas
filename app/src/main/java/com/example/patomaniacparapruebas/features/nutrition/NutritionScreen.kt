package com.example.patomaniacparapruebas.features.nutrition

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.patomaniacparapruebas.data.model.RecipesByNutrientsDTO

@Composable
fun NutritionScreen(
    modifier: Modifier,
    navController: NavController,
    nutritionViewModel: NutritionViewModel = hiltViewModel(),
    id: String?
) {
    val nutritionUiState =nutritionViewModel.nutritionUiState.collectAsState()
    Scaffold(
        modifier= Modifier
            .fillMaxSize()
            .padding(30.dp)
    ) { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                LazyColumn(
                    modifier = modifier
                ) {
                    items(nutritionUiState.value.listRecipesByNutrientsDTO) {recipesByNutrientsDTO->
                        NutritionItem(itemNutrition = recipesByNutrientsDTO)
                    }
                }
            }
        }
    }

@Composable
fun NutritionItem(
    itemNutrition: RecipesByNutrientsDTO
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = itemNutrition.titleRecipesByNutrients,
                fontSize = 15.sp,
                fontStyle = FontStyle.Normal
            )
            AsyncImage(
                model = itemNutrition.imageRecipesByNutrients,
                contentDescription = null
            )
            Text(
                text = itemNutrition.carbsRecipesByNutrients,
                fontSize = 15.sp,
                fontStyle = FontStyle.Normal
            )
            Text(
                text = itemNutrition.proteinRecipesByNutrients,
                fontSize = 15.sp,
                fontStyle = FontStyle.Normal
            )
            Text(
                text = itemNutrition.caloriesRecipesByNutrients.toString(),
                fontSize = 15.sp,
                fontStyle = FontStyle.Normal
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NutritionPreview() {
    val listPreview = listOf(
        RecipesByNutrientsDTO(
            1,
            "nutrition1",
            "https://img.spoonacular.com/recipes/658004-312x231.jpg",   //Todo-> no se ve img en Preview
            "",
            20,
            "50",
            "",
            "35"
        )
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        LazyColumn {
            items(listPreview) {
                NutritionItem(itemNutrition = it)
            }
        }
    }
}