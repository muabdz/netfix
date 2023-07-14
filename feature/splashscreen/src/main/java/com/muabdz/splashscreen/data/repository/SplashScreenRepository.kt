package com.muabdz.splashscreen.data.repository

import com.muabdz.core.wrapper.DataResource
import com.muabdz.shared.data.model.response.BaseResponse
import com.muabdz.shared.data.repository.Repository
import com.muabdz.splashscreen.data.network.datasource.SplashScreenDataSource
import com.muabdz.splashscreen.data.network.model.response.SyncResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

typealias SyncDataResponse = DataResource<BaseResponse<SyncResponse>>

interface SplashScreenRepository {
    suspend fun doUserSync() : Flow<SyncDataResponse>
}

class SplashScreenRepositoryImpl(private val dataSource: SplashScreenDataSource) : SplashScreenRepository, Repository() {
    override suspend fun doUserSync(): Flow<SyncDataResponse> {
        return flow {
            emit(safeNetworkCall { dataSource.doUserSync() })
        }
    }

}