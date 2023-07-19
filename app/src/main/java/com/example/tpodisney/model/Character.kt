package com.example.tpodisney.model

data class Character (
    val _id : Int,
    val name : String,
    val sourceUrl : String,
    val imageUrl : String,
    val createdAt : String,
    val updatedAt : String,
    val url : String,
    val __v: Int,
    val films : ArrayList<String>,
    val shortFilms : ArrayList<String>,
    val tvShows : ArrayList<String>,
    val videoGames : ArrayList<String>,
    val parkAttractions : ArrayList<String>,
    val allies : ArrayList<String>,
    val enemies : ArrayList<String>,
    var fav : Boolean
        )