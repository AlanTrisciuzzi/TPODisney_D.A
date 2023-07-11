package com.example.tpodisney.ui

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.tpodisney.R
import com.example.tpodisney.model.Character
import com.google.firebase.firestore.FirebaseFirestore

class CharacterDetailActivity : AppCompatActivity() {

    private lateinit var imgHome: ImageView
    private lateinit var imgFavoritos: ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.character_detail)
        // bindView()

        // OBTIENE LOS DATOS DE LA ANTERIOR ACTIVIDAD CHARACTER ADAPTER
        val nombre = intent.getStringExtra("nombre")
        val fill = intent.getStringExtra("fill")
        val imagen = intent.getStringExtra("imagen")
        val shortFilms = intent.getStringExtra("shortFilms")
        val tvshows = intent.getStringExtra("tvshows")
        val videogames = intent.getStringExtra("videogames")
        val parkAttractions = intent.getStringExtra("parkAttractions")
        val allies = intent.getStringExtra("allies")
        val enemies = intent.getStringExtra("enemies")
        // val fav = intent.getStringExtra("fav")



        // CAPTURA LOS VALORES PARA LUEGO UTILIZARLOS
        val img = findViewById<ImageView>(R.id.imgPersonaje)
        val txtName = findViewById<TextView>(R.id.txtName)
        val txtFill = findViewById<TextView>(R.id.txtFill)
        val txtTvshows = findViewById<TextView>(R.id.txtTvshows)
        val txtVideogames = findViewById<TextView>(R.id.txtVideogames)
        val txtShortFilms = findViewById<TextView>(R.id.txtShortFilms)
        val txtParkAttractions = findViewById<TextView>(R.id.txtParkAttractions)
        val txtAllies = findViewById<TextView>(R.id.txtAllies)
        val txtEnemies = findViewById<TextView>(R.id.txtEnemies)
        // val favv = findViewById<ImageView>(R.id.imgfav)


        // ASIGNA A CADA VALOR SU DATO CORRESPONDIENTE

        // LA LIBRERIA GLIDE ASIGNA LA IMAGEN POR SU URL A SU CORRESPONDIENTE PERSONAJE
        Glide.with(this)
            .load(imagen)
            .placeholder(R.drawable.ic_launcher_foreground)
            .into(img)

        txtName.text = nombre
        txtFill.text = fill
        txtShortFilms.text = shortFilms
        txtTvshows.text = tvshows
        txtVideogames.text = videogames
        txtParkAttractions.text = parkAttractions
        txtAllies.text = allies
        txtEnemies.text = enemies

        /*if (fav.equals("true")){
            favv.setColorFilter(Color.RED)
        }*/

    }


    /*
    private fun bindView(){
        var imgHome: ImageView = findViewById(R.id.imgHome)
        var imgFavorites: ImageView = findViewById(R.id.imgFavoritos)

        imgHome.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        imgFavorites.setOnClickListener {
            val intent = Intent(this, FavoritosActivity::class.java)
            startActivity(intent)
        }

    }*/
}