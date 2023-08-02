package com.muabdz.register.presentation.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muabdz.core.wrapper.ViewResource
import com.muabdz.register.domain.RegisterUserUseCase
import com.muabdz.shared.data.model.viewparam.UserViewParam
import kotlinx.coroutines.launch

class RegisterViewModel(private val registerUserUseCase: RegisterUserUseCase) :  ViewModel() {

    val registerResult = MutableLiveData<ViewResource<UserViewParam?>>()

    fun registerUser(
        birthdate: String,
        email: String,
        gender: String,
        password: String,
        username: String
    ) {
        viewModelScope.launch {
            registerUserUseCase(RegisterUserUseCase.RegisterParam(
                email = email,
                password = password,
                birthdate = birthdate,
                gender = gender,
                username = username
            )).collect {
                registerResult.postValue(it)
            }
        }
    }
}