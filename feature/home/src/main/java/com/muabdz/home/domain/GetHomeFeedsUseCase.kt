package com.muabdz.home.domain

import com.muabdz.core.base.BaseUseCase
import com.muabdz.core.wrapper.ViewResource
import com.muabdz.home.data.repository.HomeRepository
import com.muabdz.home.presentation.viewparam.homeitem.HomeUiItem
import com.muabdz.home.utils.mapper.SectionMapper
import com.muabdz.shared.data.model.mapper.MovieMapper
import com.muabdz.shared.utils.ext.suspendSubscribe
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetHomeFeedsUseCase(
    private val repository: HomeRepository,
    dispatcher: CoroutineDispatcher) : BaseUseCase<Nothing, List<HomeUiItem>>(dispatcher) {
    override suspend fun execute(param: Nothing?): Flow<ViewResource<List<HomeUiItem>>> = flow {
        emit(ViewResource.Loading())
        repository.fetchHomeFeeds().collect {
            it.suspendSubscribe(
                doOnSuccess = { result ->
                    val data = mutableListOf<HomeUiItem>()
                    result.payload?.data?.let { homeData ->
                        homeData.header?.let { movie ->
                            data.add(HomeUiItem.HeaderSectionItem(MovieMapper.toViewParam(movie)))
                        }
                        homeData.sections?.forEach { section ->
                            data.add(HomeUiItem.MovieSectionItem(SectionMapper.toViewParam(section)))
                        }
                    }
                    emit(ViewResource.Success(data))
                }, doOnError = { error ->
                    emit(ViewResource.Error(error.exception))
                }
            )
        }
    }

}