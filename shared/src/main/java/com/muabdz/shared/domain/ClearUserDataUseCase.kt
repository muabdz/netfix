package com.muabdz.shared.domain

import com.muabdz.core.base.BaseUseCase
import com.muabdz.core.wrapper.DataResource
import com.muabdz.core.wrapper.ViewResource
import com.muabdz.shared.data.repository.UserPreferenceRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ClearUserDataUseCase(
    private val repository: UserPreferenceRepository,
    dispatcher: CoroutineDispatcher
) : BaseUseCase<Any, Boolean>(dispatcher) {
    override suspend fun execute(param: Any?): Flow<ViewResource<Boolean>> {
        return repository.clearData().map {
            when (it) {
                is DataResource.Success -> {
                    ViewResource.Success(true)
                }
                else -> {
                    ViewResource.Error(it.exception)
                }
            }
        }
    }
}