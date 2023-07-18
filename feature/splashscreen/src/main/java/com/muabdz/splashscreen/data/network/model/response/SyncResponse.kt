package com.muabdz.splashscreen.data.network.model.response

import com.google.gson.annotations.SerializedName
import com.muabdz.shared.data.model.response.UserResponse

data class SyncResponse(
    @SerializedName("user")
    val userResponse: UserResponse? = null
)
