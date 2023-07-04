package com.example.tpodisney.data

import com.example.tpodisney.model.Character

class CharactersRepository {
    private val charactersDS = CharactersDataSource()

    suspend fun getCharacters(name : String) : ArrayList<Character> {
        return charactersDS.getCharacters(name)
    }



}