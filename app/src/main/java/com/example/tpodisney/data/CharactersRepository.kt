package com.example.tpodisney.data

import android.content.Context
import android.util.Log
import com.example.tpodisney.model.Character

class CharactersRepository {
    private val charactersDS = CharactersDataSource()
    private val _TAG = "API-DEMO"

    suspend fun getCharacters(name : String) : ArrayList<Character> {
        Log.d(_TAG, "En Repository va bien la request")
        return charactersDS.getCharacters(name)
    }

    fun getFavCharacter(context: Context, callback: (ArrayList<Character>) -> Unit) {
        charactersDS.getFavCharacter(context, callback)
    }



}