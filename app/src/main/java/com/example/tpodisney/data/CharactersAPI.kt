package com.example.tpodisney.data

import com.example.tpodisney.model.Character
import com.example.tpodisney.model.CharacterResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface CharactersAPI {
    @GET("/character")
    fun getCharacters(
        @Query ("name") name : String
    ) : Call<CharacterResponse>

}