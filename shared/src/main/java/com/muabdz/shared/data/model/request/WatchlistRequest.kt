package com.muabdz.shared.data.model.request

import com.google.gson.annotations.SerializedName

data class WatchlistRequest(
    @SerializedName("movie_id")
    val movieId: String
)
