package com.example.patomaniacparapruebas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.patomaniacparapruebas.navigation.AppNavigation
import com.example.patomaniacparapruebas.ui.theme.PatoManiacParaPruebasTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            PatoManiacParaPruebasTheme {
                AppNavigation()
                }
            }
        }
    }
