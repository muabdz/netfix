package com.muabdz.shared.data.remote.datasource

import com.muabdz.shared.data.model.request.WatchlistRequest
import com.muabdz.shared.data.model.response.BaseResponse
import com.muabdz.shared.data.remote.services.SharedFeatureApi

interface SharedFeatureApiDataSource {
    suspend fun addWatchlist(request: WatchlistRequest): BaseResponse<Any>
    suspend fun removeWatchlist(request: WatchlistRequest): BaseResponse<Any>
}

class SharedFeatureApiDataSourceImpl(private val sharedFeatureApi: SharedFeatureApi) :
    SharedFeatureApiDataSource {
    override suspend fun addWatchlist(request: WatchlistRequest): BaseResponse<Any> {
        return sharedFeatureApi.addWatchlist(request)
    }

    override suspend fun removeWatchlist(request: WatchlistRequest): BaseResponse<Any> {
        return sharedFeatureApi.removeWatchlist(request)
    }

}