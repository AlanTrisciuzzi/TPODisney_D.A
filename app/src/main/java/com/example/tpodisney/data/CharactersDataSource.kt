package com.example.tpodisney.data

import android.content.Context

import android.util.Log
import com.example.tpodisney.model.Character
import com.example.tpodisney.model.Image
import com.google.firebase.FirebaseApp
import com.google.firebase.firestore.FirebaseFirestore

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

        val result = api.getCharacters(name).execute()

        return if (result.isSuccessful){


            Log.d(_TAG, "Resultado Exitoso")
            result.body()?.data ?: ArrayList<Character>()
        } else {
            Log.e(_TAG, "Error en el llamado API: " + result.message())
            ArrayList<Character>()
        }
    }

    fun getFavCharacter (context: Context, callback: (ArrayList<Character>) -> Unit) {
        FirebaseApp.initializeApp(context)
        val database = FirebaseFirestore.getInstance()
        val characters = ArrayList<Character>()

        database.collection("favoritos").get().addOnSuccessListener() {
                result ->
            for (document in result) {
                // Accede a los datos de cada documento

                val fav = document.data
                // Haz algo con los datos del documento
                Log.d("DATASOURCE", "Favoritos traido")

                val images = fav["images"] as? ArrayList<HashMap<String,Any>> ?: ArrayList()
                val imgs : ArrayList<Image> = ArrayList()
                images.map { imgs.add(Image(it["url"] as String, (it["height"] as Long).toInt(), (it["width"] as Long).toInt())) }

                val character = Character(
                    (fav["_id"] as? Long)?.toInt() ?: 0,
                    fav["name"] as? String ?: "",
                    fav["sourceUrl"] as? String ?: "",
                    fav["imageUrl"] as? String ?: "",
                    fav["createdAt"] as? String ?: "",
                    fav["updatedAt"] as? String ?: "",
                    fav["url"] as? String ?: "",
                    (fav["__v"] as? Long)?.toInt()?: 0,

                    listOf(fav["films"] as? String ?: ""),
                    listOf(fav["shortFilms"] as? String ?: ""),
                    listOf(fav["tvShows"] as? String ?: ""),
                    listOf(fav["videoGames"] as? String ?: ""),
                    listOf(fav["parkAttractions"] as? String ?: ""),
                    listOf(fav["allies"] as? String ?: ""),
                    listOf(fav["enemies"] as? String ?: ""),
                    fav["fav"] as? Boolean ?: false,
                    imgs
                    )

                characters.add(character)
                Log.d("DATASOURCE", character.toString())
                Log.d("DATASOURCE", characters.toString())
                Log.d("DATASOURCE", "Favoritos Exitoso")
            }
            callback(characters)
        } .addOnFailureListener { e ->
            // Maneja el error de obtener los documentos
            Log.d("DATASOURCE","Error al obtener los documentos de la colección: $e")
        }

    }



}