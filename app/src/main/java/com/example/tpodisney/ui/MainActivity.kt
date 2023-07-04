package com.example.tpodisney.ui

import android.content.Intent
import android.os.Bundle
import android.widget.AbsListView.MultiChoiceModeListener
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
    //private lateinit var



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        firebaseAuth = FirebaseAuth.getInstance()
        //checkUser()

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