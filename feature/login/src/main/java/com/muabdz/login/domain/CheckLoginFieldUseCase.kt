package com.muabdz.login.domain

import com.muabdz.core.base.BaseUseCase
import com.muabdz.core.exception.FieldErrorException
import com.muabdz.core.wrapper.ViewResource
import com.muabdz.shared.utils.StringUtils
import com.muabdz.styling.R
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

typealias CheckLoginFieldResult = List<Pair<Int, Int>>

class CheckLoginFieldUseCase(dispatcher: CoroutineDispatcher) : BaseUseCase<LoginUserUseCase.Param, CheckLoginFieldResult>(dispatcher) {

    companion object {
        const val FIELD_EMAIL = 1
        const val FIELD_PASSWORD = 2
    }

    override suspend fun execute(param: LoginUserUseCase.Param?): Flow<ViewResource<CheckLoginFieldResult>> =
        flow {
            param?.let {
                val result = mutableListOf<Pair<Int, Int>>()
                isEmailValid(param.email)?.let {
                    result.add(it)
                }
                isPasswordValid(param.email)?.let {
                    result.add(it)
                }
                if (result.isEmpty()) {
                    emit(ViewResource.Success(result))
                } else {
                    emit(ViewResource.Error(FieldErrorException(), result))
                }
            }?: throw IllegalStateException("Param Required")
        }

    private fun isPasswordValid(password: String): Pair<Int, Int>? {
        return if (password.isEmpty()) {
            Pair(FIELD_PASSWORD, R.string.error_field_password)
        } else {
            null
        }
    }

    private fun isEmailValid(email: String): Pair<Int, Int>? {
        return if (StringUtils.isEmailValid(email)) {
            Pair(FIELD_EMAIL, R.string.error_field_email)
        } else {
            null
        }
    }
}