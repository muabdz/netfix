package com.muabdz.register.domain

import com.muabdz.core.base.BaseUseCase
import com.muabdz.core.exception.FieldErrorException
import com.muabdz.core.wrapper.ViewResource
import com.muabdz.register.R
import com.muabdz.register.constants.RegisterFieldConstants.BIRTHDATE_FIELD
import com.muabdz.register.constants.RegisterFieldConstants.EMAIL_FIELD
import com.muabdz.register.constants.RegisterFieldConstants.GENDER_FIELD
import com.muabdz.register.constants.RegisterFieldConstants.PASSWORD_FIELD
import com.muabdz.register.constants.RegisterFieldConstants.USERNAME_FIELD
import com.muabdz.shared.utils.StringUtils
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

typealias CheckFieldRegisterResult = List<Pair<Int, Int>>

class CheckRegisterFieldUseCase(dispatcher: CoroutineDispatcher) : BaseUseCase<RegisterUserUseCase.RegisterParam, CheckFieldRegisterResult>(
    dispatcher){

    override suspend fun execute(param: RegisterUserUseCase.RegisterParam?): Flow<ViewResource<CheckFieldRegisterResult>> =
        flow {
            param?.let {
                val result = mutableListOf<Pair<Int, Int>>()
                isEmailValid(param.email)?.let {
                    result.add(it)
                }
                isPasswordValid(param.password)?.let {
                    result.add(it)
                }
                isBirthdateValid(param.birthdate)?.let {
                    result.add(it)
                }
                isGenderValid(param.gender)?.let {
                    result.add(it)
                }
                isUsernameValid(param.username)?.let {
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
            Pair(PASSWORD_FIELD, R.string.error_field_empty)
        } else if (password.length < 8) {
            Pair(PASSWORD_FIELD, R.string.error_field_password)
        } else {
            null
        }
    }

    private fun isEmailValid(email: String): Pair<Int, Int>? {
        return if (email.isEmpty()) {
            Pair(EMAIL_FIELD, R.string.error_field_empty)
        } else if (!StringUtils.isEmailValid(email)) {
            Pair(EMAIL_FIELD, R.string.error_field_email)
        } else {
            null
        }
    }

    private fun isUsernameValid(username: String): Pair<Int, Int>? {
        return if (username.isEmpty()) {
            Pair(USERNAME_FIELD, R.string.error_field_empty)
        } else if (username.length < 8) {
            Pair(USERNAME_FIELD, R.string.error_field_username)
        } else if (username.contains(" ")) {
            Pair(USERNAME_FIELD, R.string.error_field_username_space)
        } else {
            null
        }
    }

    private fun isGenderValid(gender: String): Pair<Int, Int>? {
        return if (gender.isEmpty()) {
            Pair(GENDER_FIELD, R.string.error_field_empty)
        } else {
            null
        }
    }

    private fun isBirthdateValid(birthdate: String): Pair<Int, Int>? {
        return if (birthdate.isEmpty()) {
            Pair(BIRTHDATE_FIELD, R.string.error_field_empty)
        } else {
            null
        }
    }
}