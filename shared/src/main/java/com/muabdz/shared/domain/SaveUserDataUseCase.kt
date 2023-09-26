package com.muabdz.shared.domain

import com.muabdz.core.base.BaseUseCase
import com.muabdz.core.wrapper.ViewResource
import com.muabdz.shared.data.model.response.UserResponse
import com.muabdz.shared.data.repository.UserPreferenceRepository
import com.muabdz.shared.utils.ext.suspendSubscribe
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SaveUserDataUseCase(
    private val repository: UserPreferenceRepository,
    dispatcher: CoroutineDispatcher
) : BaseUseCase<SaveUserDataUseCase.Param, Boolean>(dispatcher) {

    override suspend fun execute(param: Param?): Flow<ViewResource<Boolean>> {
        return flow {
            param?.let {
                repository.setCurrentUser(param.user).collect {
                    it.suspendSubscribe(
                        doOnSuccess = {
                            emit(ViewResource.Success(true))
                        }, doOnError = {
                            emit(ViewResource.Error(IllegalStateException("Failed when save local data")))
                        })
                }
            }
        }
    }

    data class Param(val user: UserResponse)
}