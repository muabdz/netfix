package com.muabdz.profile.domain

import com.muabdz.core.base.BaseUseCase
import com.muabdz.core.exception.FieldErrorException
import com.muabdz.core.wrapper.ViewResource
import com.muabdz.profile.R
import com.muabdz.profile.constants.UpdateProfileFieldConstant
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

typealias CheckFieldUpdateProfileResult = List<Pair<Int, Int>>

class CheckProfileUpdateFieldUseCase(dispatcher: CoroutineDispatcher) :
    BaseUseCase<CheckProfileUpdateFieldUseCase.UpdateProfileParam, CheckFieldUpdateProfileResult>(dispatcher) {

    data class UpdateProfileParam(
        val username: String
    )

    override suspend fun execute(param: UpdateProfileParam?): Flow<ViewResource<CheckFieldUpdateProfileResult>> {
        return flow {
            param?.let {
                val result = mutableListOf<Pair<Int, Int>>()
                checkIsUsernameValid(param.username)?.let {
                    result.add(it)
                }
                if (result.isEmpty()) {
                    emit(ViewResource.Success(result))
                } else {
                    emit(ViewResource.Error(FieldErrorException(result)))
                }
            } ?: throw IllegalStateException("Param Required")
        }
    }

    private fun checkIsUsernameValid(password: String): Pair<Int, Int>? {
        return if (password.length < 8) {
            Pair(UpdateProfileFieldConstant.USERNAME_FIELD, R.string.error_text_username_less_than_min_char)
        } else if (password.isEmpty()) {
            Pair(UpdateProfileFieldConstant.USERNAME_FIELD, R.string.error_text_username_required)
        } else if (password.contains(" ")) {
            Pair(UpdateProfileFieldConstant.USERNAME_FIELD, R.string.error_text_username_no_space)
        } else {
            null
        }
    }
}