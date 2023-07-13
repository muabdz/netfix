package com.muabdz.shared.domain

import com.muabdz.core.base.BaseUseCase
import com.muabdz.core.wrapper.ViewResource
import com.muabdz.shared.data.repository.UserPreferenceRepository
import com.muabdz.shared.utils.ext.suspendSubscribe
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class GetUserTokenUseCase(
    private val repository: UserPreferenceRepository,
    dispatcher: CoroutineDispatcher
) : BaseUseCase<Nothing, String>(dispatcher) {

    override suspend fun execute(param: Nothing?): Flow<ViewResource<String>> {
        return flow {
            repository.getUserToken().map {
                it.suspendSubscribe(doOnSuccess = { result ->
                    emit(ViewResource.Success(it.payload.orEmpty()))
                }, doOnError = { error ->
                    emit(ViewResource.Error(error.exception))
                })
            }
        }
    }

}