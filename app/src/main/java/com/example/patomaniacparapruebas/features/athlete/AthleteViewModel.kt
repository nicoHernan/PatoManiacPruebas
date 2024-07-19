package com.example.patomaniacparapruebas.features.athlete

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.patomaniacparapruebas.data.database.AthletesEntity
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
class AthleteViewModel @Inject constructor (
   private val repositoryApp: RepositoryApp
): ViewModel() {
    private val _athleteUiState = MutableStateFlow(AthleteUiState() )
    val athleteUiState:StateFlow <AthleteUiState> = _athleteUiState

    init {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                if (repositoryApp.getAllAthletesDBLocal().isEmpty()) {
                    val newListOfAthletes = repositoryApp.getAthletes()
                    repositoryApp.insertAllAthletesDBLocal(

                        newListOfAthletes.map { athleteDetails ->
                            AthletesEntity(
                                athleteDetails.id,
                                athleteDetails.name,
                                athleteDetails.weight.toDouble(),
                                athleteDetails.height.toDouble(),
                            ) }
                    )
                    _athleteUiState.update {
                        _athleteUiState.value.copy(listAthleteDetails = newListOfAthletes)
                    }

                } else {
                    val athletesEntity = repositoryApp.getAllAthletesDBLocal().map { athletesEntity ->
                            AthleteDetails(
                                id = athletesEntity.id,
                                name = athletesEntity.name,
                                weight = athletesEntity.weight.toString(),
                                height = athletesEntity.height.toString(),
                            )
                        } as ArrayList <AthleteDetails>
                    _athleteUiState.update {
                        _athleteUiState.value.copy(listAthleteDetails = athletesEntity)
                    }
                }
            }
        }
    }

    fun onValueChangeName(value: String) {
        _athleteUiState.update {
            _athleteUiState.value.copy(nameAthlete = value)
        }
    }

    fun onValueChangeHeight(value: String) {
        _athleteUiState.update {
            _athleteUiState.value.copy(heightAthlete = value)
        }
    }

    fun onValueChangeWeight(value: String) {
        _athleteUiState.update {
            _athleteUiState.value.copy(weightAthlete = value)
        }
    }

    fun saveAthlete() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val athleteInfo = AthletesEntity(
                    name = _athleteUiState.value.nameAthlete,
                    weight = _athleteUiState.value.weightAthlete.toDouble(),
                    height = _athleteUiState.value.heightAthlete.toDouble()
                )
                repositoryApp.insertAthleteDBLocal(athleteInfo)
            }
        }
    }
}