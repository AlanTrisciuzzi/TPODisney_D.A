package com.example.tpodisney.ui

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tpodisney.R


class CharacterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val name: TextView = itemView.findViewById(R.id.lblname)
    val url: TextView = itemView.findViewById(R.id.lblurl)

}