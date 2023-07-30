package com.muabdz.register.data.network.services

import com.muabdz.register.data.network.model.request.RegisterRequest
import com.muabdz.shared.data.model.response.AuthResponse
import com.muabdz.shared.data.model.response.BaseResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface RegisterFeatureApi {

    @POST("/api/v1/user/register")
    suspend fun registerUser(@Body registerRequest: RegisterRequest): BaseResponse<AuthResponse>

}