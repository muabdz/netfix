package com.muabdz.profile.data.network.model.request

import com.google.gson.annotations.SerializedName

data class UpdateProfileRequest(
    @SerializedName("username")
    val username: String
)
