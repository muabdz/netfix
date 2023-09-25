package com.muabdz.profile.data.network.model.response

import com.google.gson.annotations.SerializedName
import com.muabdz.shared.data.model.response.UserResponse

data class UpdateProfileResponse(
    @SerializedName("user")
    val user: UserResponse?
)
