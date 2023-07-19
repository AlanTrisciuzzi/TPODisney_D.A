package com.example.tpodisney.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tpodisney.R
import com.example.tpodisney.model.Character

class FavoritosActivity : AppCompatActivity(){
    private lateinit var viewModel: FavoritosViewModel
    private lateinit var rvFavoritos: RecyclerView
    private lateinit var characters: ArrayList<Character>
    private lateinit var adapter: FavoritosAdapter
    val searchResults = ArrayList<Character>() // Lista para almacenar los resultados de la busquedad

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite)
        characters = ArrayList()
        bindView()
        bindViewModel()

    }

    override fun onStart() {
        super.onStart()
        viewModel = ViewModelProvider(this).get(FavoritosViewModel::class.java)
        viewModel.onStart(this){ character ->
            characters = character
            viewModel.characters.value = characters
            Log.d("FAVORITE ACTIVITY", characters.toString())
        }
    }

    private fun bindView() {
        rvFavoritos = findViewById(R.id.rvFavoritos)
        rvFavoritos.layoutManager = LinearLayoutManager(this)
        adapter = FavoritosAdapter()
        rvFavoritos.adapter = adapter


        val listOfCharacter: ImageView = findViewById(R.id.imgHome)
        listOfCharacter.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        val searchView = findViewById<SearchView>(R.id.search)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                // Aquí puedes realizar la búsqueda cuando se envía el texto de búsqueda
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                // Aquí puedes realizar la búsqueda mientras el usuario escribe el texto
                search(newText)
                return true
            }
        })
    }

    private fun search(query: String) {
        searchResults.clear()  // Limpiar los resultados de búsqueda anteriores

        for (character in characters) {
            if (character.name.contains(query, ignoreCase = true)) {
                searchResults.add(character)  // Agregar álbumes que coincidan con el nombre buscado
            }
        }

        viewModel.characters.value= searchResults

        // Aquí puedes actualizar la interfaz de usuario con los resultados de búsqueda
        // Por ejemplo, puedes mostrar los resultados en un RecyclerView o ListView
        // utilizando un adaptador personalizado.
    }

    private fun bindViewModel(){
        viewModel = ViewModelProvider(this)[FavoritosViewModel::class.java]

        viewModel.characters.observe(this){
            // Actualizar la lista de la pantalla
            adapter.Update(it)

        }
    }
}