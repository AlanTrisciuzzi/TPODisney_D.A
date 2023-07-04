package com.example.tpodisney.data

import android.util.Log
import com.example.tpodisney.model.Character

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CharactersDataSource {
    // Constantes
    private val _BASE_URL = "https://api.disneyapi.dev"
    private val _TAG = "API-DEMO"

    suspend fun getCharacters(name: String) : ArrayList<Character>{
        Log.d(_TAG, "Characters DataSource GetCharacters")
        val api = Retrofit.Builder()
                .baseUrl(_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(CharactersAPI::class.java)

        Log.d(_TAG, "La api paso por retrofit")

        var result = api.getCharacters(name).execute()

        return if (result.isSuccessful){

            /*
            val db = FirebaseFirestore.getInstance()

            db.collection("albums").document("id").set(
                hashMapOf(
                    "name" to "contenido variable",
                    "email" to "contenido variable 2"
                    )
            )

            db.collection("universities") .document("id").get().addOnSuccessListener {
                var nombre = it.get("name")
            }
            */


            Log.d(_TAG, "Resultado Exitoso")
            result.body()?.data ?: ArrayList<Character>()
        } else {
            Log.e(_TAG, "Error en el llamado API: " + result.message())
            ArrayList<Character>()
        }
    }
}