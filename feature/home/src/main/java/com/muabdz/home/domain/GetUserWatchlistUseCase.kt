package com.muabdz.home.domain

import com.muabdz.core.base.BaseUseCase
import com.muabdz.core.wrapper.ViewResource
import com.muabdz.home.data.repository.HomeRepository
import com.muabdz.shared.data.model.mapper.MovieMapper
import com.muabdz.shared.data.model.viewparam.MovieViewParam
import com.muabdz.shared.utils.ext.suspendSubscribe
import com.muabdz.shared.utils.mapper.ListMapper
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetUserWatchlistUseCase(
    private val homeRepository: HomeRepository,
    dispatcher: CoroutineDispatcher) : BaseUseCase<Nothing, List<MovieViewParam>>(
    dispatcher
) {
    override suspend fun execute(param: Nothing?): Flow<ViewResource<List<MovieViewParam>>> = flow {
        emit(ViewResource.Loading())
        homeRepository.fetchWatchList().collect { watchlist ->
            watchlist.suspendSubscribe(
                doOnSuccess = { response ->
                    val movies = response.payload?.data
                    if (movies.isNullOrEmpty()) {
                        emit(ViewResource.Empty())
                    } else {
                        emit(ViewResource.Success(ListMapper(MovieMapper).toViewParams(movies)))
                    }
                }, doOnError = { error ->
                    emit(ViewResource.Error(error.exception))
                }
            )
        }
    }

}