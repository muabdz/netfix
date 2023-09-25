package com.muabdz.profile.domain

import com.muabdz.core.base.BaseUseCase
import com.muabdz.core.wrapper.ViewResource
import com.muabdz.profile.data.repository.ProfileRepository
import com.muabdz.shared.data.model.mapper.UserMapper
import com.muabdz.shared.data.model.viewparam.UserViewParam
import com.muabdz.shared.domain.SaveUserDataUseCase
import com.muabdz.shared.utils.ext.suspendSubscribe
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow

class UpdateProfileUseCase(
    private val checkFieldUseCase: CheckProfileUpdateFieldUseCase,
    private val saveUserDataUseCase: SaveUserDataUseCase,
    private val repository: ProfileRepository,
    dispatcher: CoroutineDispatcher
) : BaseUseCase<CheckProfileUpdateFieldUseCase.UpdateProfileParam, UserViewParam?>(dispatcher) {

    override suspend fun execute(param: CheckProfileUpdateFieldUseCase.UpdateProfileParam?): Flow<ViewResource<UserViewParam?>> {
        param?.let {
            return flow {
                emit(ViewResource.Loading())
                checkFieldUseCase(param).first().suspendSubscribe(
                    doOnSuccess = {
                        repository.updateUser(param.username).collect { updateResult ->
                            updateResult.suspendSubscribe(doOnSuccess = {
                                updateResult.payload?.data?.user?.let { user ->
                                    saveUserDataUseCase(SaveUserDataUseCase.Param(user)).first()
                                        .suspendSubscribe(
                                            doOnSuccess = {
                                                if (it.payload == true) {
                                                    emit(ViewResource.Success(UserMapper.toViewParam(user)))
                                                } else {
                                                    emit(ViewResource.Error(IllegalStateException("Save user data failed")))
                                                }
                                            }, doOnError = { error ->
                                                emit(ViewResource.Error(error.exception))
                                            }
                                        )
                                }
                            }, doOnError = { error ->
                                emit(ViewResource.Error(error.exception))
                            })
                    }
                    }, doOnError = {
                        emit(ViewResource.Error(it.exception))
                    })
            }
        } ?: throw IllegalStateException("Required Param")
    }
}