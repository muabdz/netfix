package com.muabdz.home.data.repository

import com.muabdz.core.wrapper.DataResource
import com.muabdz.home.data.network.datasource.HomeDataSource
import com.muabdz.home.data.network.model.HomeFeedsResponse
import com.muabdz.shared.data.model.response.BaseResponse
import com.muabdz.shared.data.model.response.MovieResponse
import com.muabdz.shared.data.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

typealias HomeDataResource = DataResource<BaseResponse<HomeFeedsResponse>>

typealias WatchlistDataResource = DataResource<BaseResponse<List<MovieResponse>>>

interface HomeRepository {

    suspend fun fetchHomeFeeds(): Flow<HomeDataResource>

    suspend fun fetchWatchList(): Flow<WatchlistDataResource>
}

class HomeRepositoryImpl(private val dataSource: HomeDataSource): Repository(), HomeRepository {
    override suspend fun fetchHomeFeeds(): Flow<HomeDataResource> = flow {
        emit(safeNetworkCall { dataSource.fetchHomeFeeds() })
    }

    override suspend fun fetchWatchList(): Flow<WatchlistDataResource> = flow {
        emit(safeNetworkCall { dataSource.fetchWatchList() })
    }

}