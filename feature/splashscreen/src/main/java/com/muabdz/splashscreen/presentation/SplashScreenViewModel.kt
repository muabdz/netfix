package com.muabdz.splashscreen.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muabdz.core.wrapper.ViewResource
import com.muabdz.splashscreen.domain.SyncResult
import com.muabdz.splashscreen.domain.SyncUserUseCase
import kotlinx.coroutines.launch

class SplashScreenViewModel(private val syncUserUseCase: SyncUserUseCase) : ViewModel() {

    val syncResult : MutableLiveData<ViewResource<SyncResult>> = MutableLiveData()

    fun syncUser() {
        viewModelScope.launch {
            syncUserUseCase().collect{
                syncResult.value = it
            }
        }
    }

}