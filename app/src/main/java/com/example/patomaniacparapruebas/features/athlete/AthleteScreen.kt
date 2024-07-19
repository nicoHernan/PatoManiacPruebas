package com.example.patomaniacparapruebas.features.athlete

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
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
            onNavigate =  {}
        )
    }
}

@Composable
fun AthleteScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    enabled: Boolean = true,
    athleteViewModel: AthleteViewModel = hiltViewModel(),
    onNavigate:() -> Unit
) {

    val athleteUiState = athleteViewModel.athleteUiState.collectAsState()   //Todo -> cómo actualizarlo en los outlinedTextField

    Scaffold(modifier.fillMaxSize()) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Card(
                modifier = Modifier
                    .padding(30.dp)
                    .fillMaxWidth()
                    .clickable {
                        onNavigate()
                    }
            ) {
                OutlinedTextField(
                    value = athleteUiState.value.nameAthlete,
                    onValueChange = { name ->
                        athleteViewModel.onValueChangeName(name)
                    },
                    label = { Text(text = "athlete name") },
                    enabled = enabled,
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
                )
                OutlinedTextField(
                    value = athleteUiState.value.heightAthlete,
                    onValueChange = { height ->
                        athleteViewModel.onValueChangeHeight(height)
                    },
                    label = { Text(text = "athlete height") },
                    enabled = enabled,
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
                )
                OutlinedTextField(
                    value = athleteUiState.value.weightAthlete,
                    onValueChange = { weight ->
                        athleteViewModel.onValueChangeWeight(weight)
                    },
                    label = { Text(text = "athlete weight") },
                    enabled = enabled,
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
                )
            }

            Button(
                onClick = {
                    athleteViewModel.saveAthlete()
                }
            ) {
                Text(text = "Save")
            }
        }
    }
}


