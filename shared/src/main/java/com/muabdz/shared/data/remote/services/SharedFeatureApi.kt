package com.muabdz.shared.data.remote.services

import com.muabdz.shared.data.model.request.WatchlistRequest
import com.muabdz.shared.data.model.response.BaseResponse
import retrofit2.http.Body
import retrofit2.http.HTTP
import retrofit2.http.POST

interface SharedFeatureApi {
    @POST("/api/v1/watchlist")
    suspend fun addWatchlist(@Body request: WatchlistRequest): BaseResponse<Any>

    @HTTP(method = "DELETE", path = "/api/v1/watchlist", hasBody = true)
    suspend fun removeWatchlist(@Body request: WatchlistRequest): BaseResponse<Any>

}