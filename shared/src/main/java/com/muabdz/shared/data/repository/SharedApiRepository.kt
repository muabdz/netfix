package com.muabdz.shared.data.repository

import com.muabdz.core.wrapper.DataResource
import com.muabdz.shared.data.model.request.WatchlistRequest
import com.muabdz.shared.data.remote.datasource.SharedFeatureApiDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface SharedApiRepository {
    suspend fun addWatchlist(movieId: String): Flow<DataResource<Any>>

    suspend fun removeWatchlist(movieId: String): Flow<DataResource<Any>>

}

class SharedApiRepositoryImpl(
    private val dataSource: SharedFeatureApiDataSource
) : Repository(), SharedApiRepository {
    override suspend fun addWatchlist(movieId: String): Flow<DataResource<Any>> {
        return flow {
            emit(safeNetworkCall { dataSource.addWatchlist(WatchlistRequest(movieId)) })
        }
    }

    override suspend fun removeWatchlist(movieId: String): Flow<DataResource<Any>> {
        return flow {
            emit(safeNetworkCall { dataSource.removeWatchlist(WatchlistRequest(movieId)) })
        }
    }

}