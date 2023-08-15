package com.muabdz.detail.data.network.datasource

import com.muabdz.detail.data.network.service.DetailFeatureApi
import com.muabdz.shared.data.model.response.BaseResponse
import com.muabdz.shared.data.model.response.MovieResponse

interface DetailDataSource {
    suspend fun fetchMovieDetail(movieId: String): BaseResponse<MovieResponse>
}

class DetailDataSourceImpl(private val detailFeatureApi: DetailFeatureApi): DetailDataSource {
    override suspend fun fetchMovieDetail(movieId: String): BaseResponse<MovieResponse> {
        return detailFeatureApi.fetchMovieDetail(movieId)
    }

}