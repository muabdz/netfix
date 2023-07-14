package com.muabdz.splashscreen.data.network.datasource

import com.muabdz.shared.data.model.response.BaseResponse
import com.muabdz.splashscreen.data.network.model.response.SyncResponse
import com.muabdz.splashscreen.data.network.service.SplashScreenFeatureApi

interface SplashScreenDataSource {
    suspend fun doUserSync() : BaseResponse<SyncResponse>
}

class SplashScreenDataSourceImpl(val service: SplashScreenFeatureApi) : SplashScreenDataSource {
    override suspend fun doUserSync(): BaseResponse<SyncResponse> {
        return service.doUserSync()
    }
}