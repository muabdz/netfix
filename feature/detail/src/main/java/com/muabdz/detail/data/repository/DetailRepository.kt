package com.muabdz.detail.data.repository

import com.muabdz.core.wrapper.DataResource
import com.muabdz.detail.data.network.datasource.DetailDataSource
import com.muabdz.shared.data.model.response.BaseResponse
import com.muabdz.shared.data.model.response.MovieResponse
import com.muabdz.shared.data.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

typealias DetailDataResource = DataResource<BaseResponse<MovieResponse>>

interface DetailRepository {
    suspend fun fetchMovieDetail(movieId: String): Flow<DetailDataResource>
}

class DetailRepositoryImpl(private val dataSource: DetailDataSource): Repository(), DetailRepository {
    override suspend fun fetchMovieDetail(movieId: String): Flow<DetailDataResource> = flow {
        emit(safeNetworkCall { dataSource.fetchMovieDetail(movieId) })
    }

}