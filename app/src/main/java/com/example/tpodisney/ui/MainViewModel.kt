package com.example.tpodisney.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tpodisney.data.CharactersRepository
import com.example.tpodisney.model.Character

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext
import kotlin.coroutines.CoroutineContext


class MainViewModel : ViewModel() {
 // Consatantes
    private val _TAG = "API-DEMO"
    private val coroutineContext: CoroutineContext = newSingleThreadContext("uadedemo")
    private val scope = CoroutineScope(coroutineContext)


 // Dependencias
    private val charactersRepo = CharactersRepository()

 // Propiedades
    var characters = MutableLiveData<ArrayList<Character>>()
    var name = ""


 // Funciones
    fun onStart(){
     Log.d(_TAG, "onStart")
        // Cargar los datos de los personajes
        scope.launch {
            kotlin.runCatching {
                charactersRepo.getCharacters(name)
            }.onSuccess {
                Log.d(_TAG , "Character onSuccess")
                characters.postValue(it)
            }.onFailure {
                Log.e(_TAG, "Characters Error: " + it)

            }
        }
    }

}