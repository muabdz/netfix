package com.muabdz.home.data.network.datasource

import com.muabdz.home.data.network.model.HomeFeedsResponse
import com.muabdz.home.data.network.service.HomeFeatureService
import com.muabdz.shared.data.model.response.BaseResponse
import com.muabdz.shared.data.model.response.MovieResponse

interface HomeDataSource {
    suspend fun fetchHomeFeeds(): BaseResponse<HomeFeedsResponse>

    suspend fun fetchWatchList(): BaseResponse<List<MovieResponse>>
}

class HomeDataSourceImpl(private val api: HomeFeatureService): HomeDataSource {
    override suspend fun fetchHomeFeeds(): BaseResponse<HomeFeedsResponse> {
        return api.fetchHomeFeeds()
    }

    override suspend fun fetchWatchList(): BaseResponse<List<MovieResponse>> {
        return api.fetchWatchList()
    }

}