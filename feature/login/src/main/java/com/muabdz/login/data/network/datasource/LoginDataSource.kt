package com.muabdz.login.data.network.datasource

import com.muabdz.login.data.network.model.request.LoginRequest
import com.muabdz.login.data.service.LoginFeatureApi
import com.muabdz.shared.data.model.response.AuthResponse
import com.muabdz.shared.data.model.response.BaseResponse

interface LoginDataSource {
    suspend fun loginUser(loginRequest: LoginRequest): BaseResponse<AuthResponse>
}

class LoginDataSourceImpl(private val api: LoginFeatureApi): LoginDataSource {
    override suspend fun loginUser(loginRequest: LoginRequest): BaseResponse<AuthResponse> {
        return api.loginUser(loginRequest)
    }

}