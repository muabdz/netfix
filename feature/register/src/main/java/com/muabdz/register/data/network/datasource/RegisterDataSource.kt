package com.muabdz.register.data.network.datasource

import com.muabdz.register.data.network.model.request.RegisterRequest
import com.muabdz.register.data.network.services.RegisterFeatureApi
import com.muabdz.shared.data.model.response.AuthResponse
import com.muabdz.shared.data.model.response.BaseResponse

interface RegisterDataSource {

    suspend fun registerUser(registerRequest: RegisterRequest): BaseResponse<AuthResponse>

}

class RegisterDataSourceImpl(private val api: RegisterFeatureApi): RegisterDataSource {
    override suspend fun registerUser(registerRequest: RegisterRequest): BaseResponse<AuthResponse> {
        return api.registerUser(registerRequest)
    }

}