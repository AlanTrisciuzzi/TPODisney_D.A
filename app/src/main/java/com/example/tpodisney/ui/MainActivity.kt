package com.example.tpodisney.ui

import android.os.Bundle
import android.widget.AbsListView.MultiChoiceModeListener
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tpodisney.R
import com.example.tpodisney.model.Character

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel : MainViewModel
    private lateinit var rvCharacter: RecyclerView
    private lateinit var adapter: CharactersAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
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




}