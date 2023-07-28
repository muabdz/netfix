package com.muabdz.login.data.repository

import com.muabdz.core.wrapper.DataResource
import com.muabdz.login.data.network.datasource.LoginDataSource
import com.muabdz.login.data.network.model.request.LoginRequest
import com.muabdz.shared.data.model.response.AuthResponse
import com.muabdz.shared.data.model.response.BaseResponse
import com.muabdz.shared.data.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

typealias LoginDataResource = DataResource<BaseResponse<AuthResponse>>

interface LoginRepository {
    suspend fun loginUser(
        email: String,
        password: String
    ) :  Flow<LoginDataResource>
}

class LoginRepositoryImpl(private val loginDataSource: LoginDataSource): LoginRepository, Repository() {
    override suspend fun loginUser(email: String, password: String): Flow<LoginDataResource> {
        return flow {
            emit(safeNetworkCall { loginDataSource.loginUser(LoginRequest(email, password))
            })
        }
    }

}