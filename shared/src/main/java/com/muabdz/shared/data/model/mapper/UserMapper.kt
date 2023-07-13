package com.muabdz.shared.data.model.mapper

import com.muabdz.shared.data.model.response.UserResponse
import com.muabdz.shared.data.model.viewparam.UserViewParam
import com.muabdz.shared.utils.ViewParamMapper

object UserMapper : ViewParamMapper<UserResponse, UserViewParam> {
    override fun toViewParam(dataObject: UserResponse?): UserViewParam = UserViewParam(
        email = dataObject?.email.orEmpty(),
        birthdate = dataObject?.birthdate.orEmpty(),
        gender = dataObject?.gender ?: -1,
        id = dataObject?.id ?: -1,
        username = dataObject?.username.orEmpty()
    )
}