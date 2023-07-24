package com.muabdz.login.domain

import com.muabdz.core.base.BaseUseCase
import com.muabdz.core.wrapper.ViewResource
import com.muabdz.login.data.repository.LoginRepository
import com.muabdz.shared.data.model.mapper.UserMapper
import com.muabdz.shared.data.model.viewparam.UserViewParam
import com.muabdz.shared.domain.SaveAuthDataUseCase
import com.muabdz.shared.utils.ext.suspendSubscribe
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow

class LoginUserUseCase(
    private val checkLoginFieldUseCase: CheckLoginFieldUseCase,
    private val saveAuthDataUseCase: SaveAuthDataUseCase,
    private val repository: LoginRepository,
    dispatcher: CoroutineDispatcher
) : BaseUseCase<LoginUserUseCase.Param, UserViewParam?>(dispatcher) {
    data class Param(val email: String, val password: String)

    override suspend fun execute(param: Param?): Flow<ViewResource<UserViewParam?>> {
        return flow {
            param?.let {
                emit(ViewResource.Loading())
                checkLoginFieldUseCase(param).first().suspendSubscribe(
                    doOnSuccess = { _ ->
                        repository.loginUser(param.email, param.password).collect { loginResult ->
                            loginResult.suspendSubscribe(
                                doOnSuccess = {
                                    val result = loginResult.payload?.data
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
}