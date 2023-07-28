package com.muabdz.login.domain

import com.muabdz.core.base.BaseUseCase
import com.muabdz.core.exception.FieldErrorException
import com.muabdz.core.wrapper.ViewResource
import com.muabdz.login.R
import com.muabdz.login.constants.LoginFieldConstants.FIELD_EMAIL
import com.muabdz.login.constants.LoginFieldConstants.FIELD_PASSWORD
import com.muabdz.shared.utils.StringUtils
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

typealias CheckLoginFieldResult = List<Pair<Int, Int>>

class CheckLoginFieldUseCase(dispatcher: CoroutineDispatcher) : BaseUseCase<LoginUserUseCase.Param, CheckLoginFieldResult>(dispatcher) {

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
                    emit(ViewResource.Error(FieldErrorException(result), result))
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
        return if (email.isEmpty()) {
            Pair(FIELD_EMAIL, R.string.error_field_email_empty)
        } else if (!StringUtils.isEmailValid(email)) {
            Pair(FIELD_EMAIL, R.string.error_field_email)
        } else {
            null
        }
    }
}