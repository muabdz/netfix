package com.muabdz.profile.data.network.datasource

import com.muabdz.profile.data.network.model.request.UpdateProfileRequest
import com.muabdz.profile.data.network.model.response.UpdateProfileResponse
import com.muabdz.profile.data.network.service.ProfileFeatureApi
import com.muabdz.shared.data.model.response.BaseResponse

interface ProfileDataSource {
    suspend fun updateUser(updateProfileRequest: UpdateProfileRequest): BaseResponse<UpdateProfileResponse>
}

class ProfileDataSourceImpl(
    private val service: ProfileFeatureApi
): ProfileDataSource {
    override suspend fun updateUser(updateProfileRequest: UpdateProfileRequest): BaseResponse<UpdateProfileResponse> {
        return service.updateProfile(updateProfileRequest)
    }

}