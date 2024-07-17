package com.example.patomaniacparapruebas.features.athlete


data class AthleteUiState(
    val listAthleteDetails: ArrayList <AthleteDetails> = ArrayList()
    //val isEntryValid: Boolean = false
)

data class AthleteDetails (     //onValueChange tiene parametro string
    val id: Int = 0,
    val name: String = "",
    val weight: String = "",
    val height: String = ""
)