package com.muabdz.shared.data.local.datastore

import kotlinx.coroutines.flow.Flow

interface UserPreferenceDataSource {
    suspend fun clearData()
    suspend fun getUserToken() : Flow<String>
    suspend fun setUserToken(newUserToken: String)
    suspend fun isUserLoggedIn() : Flow<Boolean>
    suspend fun setUserLoginStatus(isUserLoggedIn: Boolean)
    // TODO: get and set user data
}