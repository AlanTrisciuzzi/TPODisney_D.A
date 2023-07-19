package com.example.tpodisney.ui

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tpodisney.data.CharactersRepository
import com.example.tpodisney.model.Character
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext
import kotlin.coroutines.CoroutineContext

class FavoritosViewModel : ViewModel(){

    val characters: MutableLiveData<ArrayList<Character>> = MutableLiveData()

    //Constantes

    private val coroutineContext: CoroutineContext = newSingleThreadContext("uadedemo")
    private val scope = CoroutineScope(coroutineContext)

    // Dependencias
    private val characterRepo = CharactersRepository()

    fun onStart(context: Context, callback: (ArrayList<Character>)-> Unit){
        // Cargar los datos de los Charcters
        scope.launch {
            kotlin.runCatching {
                characterRepo.getFavCharacter(context,callback)
            }.onSuccess {
                Log.d("FAVORITE VIEW MODEL", "Albums Fav on SuccesS" + it.toString())
            }.onFailure {
                Log.e("FAVORITE VIEW MODEL", "FAVORITE Error: " + it)
            }
        }
    }
}