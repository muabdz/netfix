package com.muabdz.login.presentation.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muabdz.core.wrapper.ViewResource
import com.muabdz.login.domain.LoginUserUseCase
import com.muabdz.shared.data.model.viewparam.UserViewParam
import kotlinx.coroutines.launch

class LoginViewModel(private val loginUserUseCase: LoginUserUseCase): ViewModel() {
    val loginResult = MutableLiveData<ViewResource<UserViewParam?>>()

    fun loginUser(email: String, password: String) {
        viewModelScope.launch {
            loginUserUseCase(LoginUserUseCase.Param(email, password)).collect {
                loginResult.postValue(it)
            }
        }
    }
}