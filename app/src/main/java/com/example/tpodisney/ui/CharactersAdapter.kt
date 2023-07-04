package com.example.tpodisney.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tpodisney.R
import com.example.tpodisney.model.Character

class CharactersAdapter : RecyclerView.Adapter<CharacterViewHolder>(){

    var items : MutableList<Character> = ArrayList<Character>()



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.character_item, parent, false)
        return CharacterViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.name.text = items[position].name

    }

    fun Update(lista: MutableList<Character>){
        items = lista
        this.notifyDataSetChanged()
    }

}