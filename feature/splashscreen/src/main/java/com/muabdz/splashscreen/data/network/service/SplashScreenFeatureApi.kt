package com.muabdz.splashscreen.data.network.service

import com.muabdz.shared.data.model.response.BaseResponse
import com.muabdz.splashscreen.data.network.model.response.SyncResponse
import retrofit2.http.GET

interface SplashScreenFeatureApi {
    @GET("/api/v1/sync")
    suspend fun doUserSync() : BaseResponse<SyncResponse>
}