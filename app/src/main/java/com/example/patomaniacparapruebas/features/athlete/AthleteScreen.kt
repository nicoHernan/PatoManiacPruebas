package com.example.patomaniacparapruebas.features.athlete

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.patomaniacparapruebas.navigation.AppScreens
import com.example.patomaniacparapruebas.ui.theme.PatoManiacParaPruebasTheme

@Preview (showBackground = true)
@Composable
fun AthleteScreenPreview() {
    PatoManiacParaPruebasTheme {
        AthleteScreen(
            navController = rememberNavController(),
            modifier = Modifier,
            enabled = true,
            athleteDetails = AthleteDetails(
                name = "nico Hernan", weight = "70 kg", height = "1.75 m"
            ),
            onValueChange = {},
      //      athleteViewModel = hiltViewModel()
        )
    }
}

@Composable
fun AthleteScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    enabled: Boolean = true,
    athleteDetails: AthleteDetails,
    onValueChange: (AthleteDetails) -> Unit = {},

    //athleteViewModel: AthleteViewModel = hiltViewModel(),
) {

    //val athleteUiState = athleteViewModel.athleteUiState.collectAsState()   //Todo -> cómo actualizarlo en los outlinedTextField

    Surface(
        modifier = modifier
    ) {
        //Todo -> cómo dar color de fondo con Surface?
        Scaffold(
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .wrapContentSize()
            ) {


                OutlinedTextField(
                    value = athleteDetails.name,
                    onValueChange = { name ->
                        onValueChange(
                            athleteDetails.copy(weight = name)
                        )
                    },
                    label = { Text(text = "athlete name") },
                    enabled = enabled,
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
                )
                OutlinedTextField(
                    value = athleteDetails.height,
                    onValueChange = { height ->
                        onValueChange(
                            athleteDetails.copy(height = height)
                        )
                    },
                    label = { Text(text = "athlete height") },
                    enabled = enabled,
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
                )
                OutlinedTextField(
                    value = athleteDetails.weight,
                    onValueChange = { weight ->
                        onValueChange(
                            athleteDetails.copy(weight = weight)
                        )
                    },
                    label = { Text(text = "athlete weight") },
                    enabled = enabled,
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
                )
            }
        }
    }
}


