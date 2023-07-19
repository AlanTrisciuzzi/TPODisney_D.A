package com.example.tpodisney.ui

import android.content.Intent
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tpodisney.R


class CharacterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val name: TextView = itemView.findViewById(R.id.txtname)
    val heart: ImageView = itemView.findViewById(R.id.imgFavRecycler)

}