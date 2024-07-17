package com.example.patomaniacparapruebas.features.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.patomaniacparapruebas.R
import com.example.patomaniacparapruebas.ui.theme.PatoManiacParaPruebasTheme

@Composable
fun HomeScreen(
    navController: NavController,
    goToAthleteDetail: () -> Unit,
    modifier: Modifier = Modifier
        .fillMaxSize(),
    enabled: Boolean = true
) {
    Box(
        modifier = modifier
    ) {

        Image(
            painter = painterResource(id = R.drawable.dumbbell_pato_maniac),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize(),
            contentScale = ContentScale.Crop    //termina de ajustar dimensiones
        )
        Column(
            modifier = modifier,
            verticalArrangement = Arrangement.Bottom,   //Todo -> sin efecto
            horizontalAlignment = Alignment.End
        ) {
            var inputNameUser:String

            OutlinedTextField(
                value = "inputNameUser",    //Todo -> pasar nameUser a la AthleteScreen
                onValueChange = {text ->
                    inputNameUser = text },
                label = { Text(text = "input name user") },
                enabled = enabled,
                singleLine = true
            )
            Button(
                onClick = goToAthleteDetail,
                modifier = Modifier
            ) {
                Text(stringResource(id = R.string.button_home))
            }
        }
    }
}

@Preview (showBackground = true)
@Composable
fun HomeScreenPreview(){
    PatoManiacParaPruebasTheme {
        HomeScreen(
            modifier = Modifier,
            navController = rememberNavController(),
            goToAthleteDetail = {},
            enabled = true
        )
    }
}