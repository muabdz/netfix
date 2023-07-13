package com.muabdz.shared.data.model.viewparam

import com.google.gson.annotations.SerializedName

class MovieViewParam (
    val cast: List<String>,
    val category: List<String>,
    val director: String,
    val filmRate: String,
    val id: Int,
    val isUserWatchlist: Boolean,
    val overview: String,
    val posterUrl: String,
    val releaseDate: String,
    val runtime: Int,
    val title: String,
    val trailerUrl: String,
    val videoUrl: String
)