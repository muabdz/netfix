package com.muabdz.home.data.network.service

import com.muabdz.home.data.network.model.HomeFeedsResponse
import com.muabdz.shared.data.model.response.BaseResponse
import com.muabdz.shared.data.model.response.MovieResponse
import retrofit2.http.GET

interface HomeFeatureApi {

    @GET("api/v1/homepage")
    suspend fun fetchHomeFeeds(): BaseResponse<HomeFeedsResponse>

    @GET("api/v1/watchlist")
    suspend fun fetchWatchList(): BaseResponse<List<MovieResponse>>

}