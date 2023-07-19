package com.example.tpodisney.model

data class Character(
    val _id: Int,
    val name: String,
    val sourceUrl: String,
    val imageUrl: String,
    val createdAt: String,
    val updatedAt: String,
    val url: String,
    val __v: Int,
    val films: List<String>,
    val shortFilms: List<String>,
    val tvShows: List<String>,
    val videoGames: List<String>,
    val parkAttractions: List<String>,
    val allies: List<String>,
    val enemies: List<String>,
    var fav: Boolean,
    val images: ArrayList<Image>,
        )