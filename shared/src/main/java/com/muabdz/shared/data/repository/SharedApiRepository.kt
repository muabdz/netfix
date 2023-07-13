package com.muabdz.shared.data.repository

import com.muabdz.core.wrapper.DataResource
import com.muabdz.shared.data.model.request.WatchlistRequest
import com.muabdz.shared.data.remote.services.SharedFeatureApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface SharedApiRepository {
    suspend fun addWatchlist(movieId: String): Flow<DataResource<Any>>

    suspend fun removeWatchlist(movieId: String): Flow<DataResource<Any>>

}

class SharedApiRepositoryImpl(
    private val service: SharedFeatureApi
) : Repository(), SharedApiRepository {
    override suspend fun addWatchlist(movieId: String): Flow<DataResource<Any>> {
        return flow {
            emit(safeNetworkCall { service.addWatchlist(WatchlistRequest(movieId)) })
        }
    }

    override suspend fun removeWatchlist(movieId: String): Flow<DataResource<Any>> {
        return flow {
            emit(safeNetworkCall { service.removeWatchlist(WatchlistRequest(movieId)) })
        }
    }

}