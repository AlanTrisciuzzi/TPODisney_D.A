package com.example.tpodisney.ui

import android.content.Intent
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tpodisney.R
import com.example.tpodisney.model.Character

class FavoritosAdapter : RecyclerView.Adapter<CharacterViewHolder>() {
    var items: MutableList<Character> = ArrayList<Character>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.character_item, parent, false)
        return CharacterViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.name.text = items[position].name
        holder.heart.setColorFilter(Color.YELLOW)

        /// Click en Character Item.
        holder.itemView.setOnClickListener {
            try {
                val nombre = items[position].name
                val fill = items[position].films.toString()
                val imagen = items[position].imageUrl
                val shortFilms = items[position].shortFilms.toString()
                val tvshows = items[position].tvShows.toString()
                val videogames = items[position].videoGames.toString()
                val parkAttractions = items[position].parkAttractions.toString()
                val allies = items[position].allies.toString()
                val enemies = items[position].enemies.toString()
                val fav = items[position].fav.toString()

                // Inicio de la otra activity
                val character = items[position]

                val intent = Intent(holder.itemView.context, CharacterDetailActivity::class.java)
                intent.putExtra("nombre", "Nombre: ${character.name ?: "None"}")
                intent.putExtra("imagen", character.imageUrl)
                intent.putExtra("fill", "Fill: ${items[position].films.toList().joinToString(", ")}")
                intent.putExtra("shortFilms", "Short Films: ${if (character.shortFilms.isEmpty()) "None" else character.shortFilms.toList().take(1).joinToString(", ") { it.toString() ?: "None" }}")
                intent.putExtra("tvshows", "TV Shows: ${if (character.tvShows.isEmpty()) "None" else character.tvShows.toList().take(1).joinToString(", ") { it.toString() ?: "None" }}")
                intent.putExtra("videogames", "Video Games: ${if (character.videoGames.isEmpty()) "None" else character.videoGames.toList().take(1).joinToString(", ") { it.toString() ?: "None" }}")
                intent.putExtra("parkAttractions", "Park Attractions: ${if (character.parkAttractions.isEmpty()) "None" else character.parkAttractions.toList().take(1).joinToString(", ") { it.toString() ?: "None" }}")
                intent.putExtra("allies", "Allies: ${if (character.allies.isEmpty()) "None" else character.allies.toList().take(1).joinToString(", ") { it.toString() ?: "None" }}")
                intent.putExtra("enemies", "Enemies: ${if (character.enemies.isEmpty()) "None" else character.enemies.toList().take(1).joinToString(", ") { it.toString() ?: "None" }}")
                intent.putExtra("fav", fav)
                holder.itemView.context.startActivity(intent)
            } catch (e: Exception) {
                Log.e("API-DEMO", "Error al iniciar la otra activity: ${e.message}")
            }
        }
    }
    fun Update (lista: MutableList<Character>){
        items = lista
        this.notifyDataSetChanged()
    }

}