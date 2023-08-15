package com.muabdz.detail.data.network.service

import com.muabdz.shared.data.model.response.BaseResponse
import com.muabdz.shared.data.model.response.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface DetailFeatureApi {

    @GET("/api/v1/movie/{{movie_id}")
    suspend fun fetchMovieDetail(@Path("movie_id") movieId: String): BaseResponse<MovieResponse>
}