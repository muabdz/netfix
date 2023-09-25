package com.muabdz.profile.data.network.service

import com.muabdz.profile.data.network.model.request.UpdateProfileRequest
import com.muabdz.profile.data.network.model.response.UpdateProfileResponse
import com.muabdz.shared.data.model.response.BaseResponse
import retrofit2.http.Body
import retrofit2.http.PUT

interface ProfileFeatureApi {

    @PUT("/api/v1/user/")
    suspend fun updateProfile(@Body updateProfileRequest: UpdateProfileRequest): BaseResponse<UpdateProfileResponse>
}