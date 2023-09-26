package com.muabdz.profile.presentation.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muabdz.core.wrapper.ViewResource
import com.muabdz.profile.domain.CheckProfileUpdateFieldUseCase
import com.muabdz.profile.domain.UpdateProfileUseCase
import com.muabdz.shared.data.model.viewparam.UserViewParam
import com.muabdz.shared.domain.ClearUserDataUseCase
import com.muabdz.shared.domain.GetCurrentUserUseCase
import kotlinx.coroutines.launch

class ProfileViewModel (
    private val updateProfileUseCase: UpdateProfileUseCase,
    private val clearUserDataUseCase: ClearUserDataUseCase,
    private val getCurrentUserUseCase: GetCurrentUserUseCase
) : ViewModel() {

    val updateProfileResult = MutableLiveData<ViewResource<UserViewParam?>>()
    val logoutResult = MutableLiveData<ViewResource<Boolean>>()
    val currentUserResult = MutableLiveData<ViewResource<UserViewParam>>()

    fun updateProfileData(username: String) {
        viewModelScope.launch {
            updateProfileUseCase(CheckProfileUpdateFieldUseCase.UpdateProfileParam(username = username))
                .collect {
                    updateProfileResult.postValue(it)
                }
        }
    }

    fun doLogout() {
        viewModelScope.launch {
            clearUserDataUseCase().collect {
                logoutResult.postValue(it)
            }
        }
    }

    fun getCurrentUser() {
        viewModelScope.launch {
            getCurrentUserUseCase().collect {
                currentUserResult.postValue(it)
            }
        }
    }
}