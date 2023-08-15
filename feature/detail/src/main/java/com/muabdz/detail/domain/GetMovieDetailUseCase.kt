package com.muabdz.detail.domain

import com.muabdz.core.base.BaseUseCase
import com.muabdz.core.wrapper.ViewResource
import com.muabdz.detail.data.repository.DetailRepository
import com.muabdz.shared.data.model.mapper.MovieMapper
import com.muabdz.shared.data.model.viewparam.MovieViewParam
import com.muabdz.shared.utils.ext.suspendSubscribe
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetMovieDetailUseCase(
    private val repository: DetailRepository,
    dispatcher: CoroutineDispatcher) : BaseUseCase<String, MovieViewParam>(dispatcher) {
    override suspend fun execute(param: String?): Flow<ViewResource<MovieViewParam>> = flow {
        emit(ViewResource.Loading())
        repository.fetchMovieDetail(param.orEmpty()).collect { detail ->
            detail.suspendSubscribe(
                doOnSuccess = { response ->
                    val movie = response.payload?.data
                    emit(ViewResource.Success(MovieMapper.toViewParam(movie)))
                },
                doOnError = { error ->
                    emit(ViewResource.Error(error.exception))
                }
            )

        }
    }

}