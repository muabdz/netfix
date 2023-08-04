package com.muabdz.shared.domain

import com.muabdz.core.base.BaseUseCase
import com.muabdz.core.wrapper.DataResource
import com.muabdz.core.wrapper.ViewResource
import com.muabdz.shared.data.model.mapper.UserMapper
import com.muabdz.shared.data.model.viewparam.UserViewParam
import com.muabdz.shared.data.repository.UserPreferenceRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetCurrentUserUseCase(
    private val repository: UserPreferenceRepository,
    dispatcher: CoroutineDispatcher) : BaseUseCase<Nothing, UserViewParam>(
    dispatcher
) {
    override suspend fun execute(param: Nothing?): Flow<ViewResource<UserViewParam>> = repository.getCurrentUser().map {
        when (it) {
            is DataResource.Success -> {
                ViewResource.Success(UserMapper.toViewParam(it.payload))
            }
            is DataResource.Error -> {
                ViewResource.Error(it.exception)
            }
        }
    }
}