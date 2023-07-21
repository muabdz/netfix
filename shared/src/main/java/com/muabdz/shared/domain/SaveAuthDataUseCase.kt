package com.muabdz.shared.domain

import com.muabdz.core.base.BaseUseCase
import com.muabdz.core.wrapper.DataResource
import com.muabdz.core.wrapper.ViewResource
import com.muabdz.shared.data.model.response.UserResponse
import com.muabdz.shared.data.repository.UserPreferenceRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow

class SaveAuthDataUseCase(
    private val repository: UserPreferenceRepository,
    dispatcher: CoroutineDispatcher) : BaseUseCase<SaveAuthDataUseCase.Param, Boolean>(
    dispatcher){

    data class Param(val isLoggedIn: Boolean, val autToken: String, val user: UserResponse)

    override suspend fun execute(param: Param?): Flow<ViewResource<Boolean>>  = flow {
        param?.let {
            val saveUser = repository.setCurrentUser(param.user).first()
            val saveToken = repository.updateUserToken(param.autToken).first()
            val saveLoginStatus = repository.updateUserLoginStatus(param.isLoggedIn).first()

            if (saveUser is DataResource.Success &&
                    saveToken is DataResource.Success &&
                    saveLoginStatus is DataResource.Success) {
                emit(ViewResource.Success(true))
            } else {
                emit(ViewResource.Error(IllegalStateException("Failed to save local data")))
            }
        }
    }
}