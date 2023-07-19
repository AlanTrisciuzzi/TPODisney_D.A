package com.example.tpodisney.ui

import android.content.Intent
import android.media.Image
import android.os.Bundle
import android.util.Log
import android.widget.AbsListView.MultiChoiceModeListener
import android.widget.Button
import android.widget.ImageView
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tpodisney.R
import com.example.tpodisney.model.Character
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel : MainViewModel
    private lateinit var rvCharacter: RecyclerView
    private lateinit var adapter: CharactersAdapter
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var characters: ArrayList<Character>
    val searchResults = ArrayList<Character>() // Lista para almacenar los resultados de la busquedad


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        firebaseAuth = FirebaseAuth.getInstance()
        // Chequeo de Usuario
        Log.d("API-DEMO", "Arranca bien")
        checkUser()
        Log.d("API-DEMO", "Chequeo Exitoso")
        bindView()
        bindViewModel()

    }

    override fun onStart() {
        super.onStart()
        viewModel.onStart()
    }

    private fun bindView(){
        rvCharacter = findViewById(R.id.rvCharacters)
        rvCharacter.layoutManager = LinearLayoutManager(this)
        adapter = CharactersAdapter()
        rvCharacter.adapter = adapter

        val fav: ImageView = findViewById(R.id.imgFavoritosHome)
        fav.setOnClickListener {
            val intent = Intent(this, FavoritosActivity::class.java)
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
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.characters.observe(this){
            // Actualizar la lista de la pantalla
            adapter.Update(it)
        }
    }

    private fun checkUser(){
        val firebaseUser = firebaseAuth.currentUser
        if (firebaseUser == null){
            // Usuario no Logueado
            startActivity(Intent(this,LoginActivity::class.java))
            finish()
        }
    }



}