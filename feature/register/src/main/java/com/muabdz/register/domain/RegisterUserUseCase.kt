package com.muabdz.register.domain

import com.muabdz.core.base.BaseUseCase
import com.muabdz.core.wrapper.ViewResource
import com.muabdz.register.data.repository.RegisterRepository
import com.muabdz.shared.data.model.mapper.UserMapper
import com.muabdz.shared.data.model.viewparam.UserViewParam
import com.muabdz.shared.domain.SaveAuthDataUseCase
import com.muabdz.shared.utils.ext.suspendSubscribe
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow

class RegisterUserUseCase(
    private val checkRegisterFieldUseCase: CheckRegisterFieldUseCase,
    private val saveAuthDataUseCase: SaveAuthDataUseCase,
    private val repository: RegisterRepository,
    dispatcher: CoroutineDispatcher
) : BaseUseCase<RegisterUserUseCase.RegisterParam, UserViewParam?>(dispatcher) {

    override suspend fun execute(param: RegisterParam?): Flow<ViewResource<UserViewParam?>> {
        return flow {
            param?.let {
                emit(ViewResource.Loading())
                checkRegisterFieldUseCase(param).first().suspendSubscribe(
                    doOnSuccess = { _ ->
                        repository.registerUser(param.birthdate, param.email, param.gender, param.password, param.username).collect { registerResult ->
                            registerResult.suspendSubscribe(
                                doOnSuccess = {
                                    val result = registerResult.payload?.data
                                    val token = result?.token
                                    val user = result?.user
                                    if (!token.isNullOrEmpty() && user != null) {
                                        saveAuthDataUseCase(SaveAuthDataUseCase.Param(true, token, user)).collect {
                                            it.suspendSubscribe(
                                                doOnSuccess = {
                                                    emit(ViewResource.Success(UserMapper.toViewParam(user)))
                                                },
                                                doOnError = { error ->
                                                    emit(ViewResource.Error(error.exception))
                                                }
                                            )
                                        }
                                    }
                                }, doOnError = { error ->
                                    emit(ViewResource.Error(error.exception))
                                }
                            )
                        }
                    },
                    doOnError = { error ->
                        emit(ViewResource.Error(error.exception))
                    })
            } ?: throw IllegalStateException("Param Required")
        }
    }

    data class RegisterParam(
        val birthdate: String,
        val email: String,
        val gender: String,
        val password: String,
        val username: String
    )
}