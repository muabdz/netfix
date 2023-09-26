package com.muabdz.profile.data.repository

import com.muabdz.core.wrapper.DataResource
import com.muabdz.profile.data.network.datasource.ProfileDataSource
import com.muabdz.profile.data.network.model.request.UpdateProfileRequest
import com.muabdz.profile.data.network.model.response.UpdateProfileResponse
import com.muabdz.shared.data.model.response.BaseResponse
import com.muabdz.shared.data.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

typealias UpdateUserDataResponse = DataResource<BaseResponse<UpdateProfileResponse>>

interface ProfileRepository {
    suspend fun updateUser(username: String) : Flow<UpdateUserDataResponse>
}

class ProfileRepositoryImpl(
    private val dataSource: ProfileDataSource
) : ProfileRepository, Repository() {
    override suspend fun updateUser(username: String): Flow<UpdateUserDataResponse> {
        return flow {
            emit(safeNetworkCall {
                dataSource.updateUser(
                    UpdateProfileRequest(
                        username
                    )
                )
            })
        }
    }

}