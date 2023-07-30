package com.muabdz.register.data.repository

import com.muabdz.core.wrapper.DataResource
import com.muabdz.register.data.network.datasource.RegisterDataSource
import com.muabdz.register.data.network.model.request.RegisterRequest
import com.muabdz.shared.data.model.response.AuthResponse
import com.muabdz.shared.data.model.response.BaseResponse
import com.muabdz.shared.data.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

typealias RegisterDataResource = DataResource<BaseResponse<AuthResponse>>

interface RegisterRepository {

    suspend fun registerUser (
        birthdate: String,
        email: String,
        gender: String,
        password: String,
        username: String
    ) : Flow<RegisterDataResource>
}

class RegisterRepositoryImpl(private val dataSource: RegisterDataSource) : RegisterRepository, Repository() {
    override suspend fun registerUser(
        birthdate: String,
        email: String,
        gender: String,
        password: String,
        username: String
    ): Flow<RegisterDataResource> {
        return flow {
            emit(safeNetworkCall {
                dataSource.registerUser(
                    RegisterRequest(
                        birthdate = birthdate,
                        email = email,
                        gender = gender,
                        password = password,
                        username = username
                    )
                )
            })
        }
    }

}