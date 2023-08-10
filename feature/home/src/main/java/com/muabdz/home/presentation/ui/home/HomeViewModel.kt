package com.muabdz.home.presentation.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muabdz.core.wrapper.ViewResource
import com.muabdz.home.domain.GetHomeFeedsUseCase
import com.muabdz.home.domain.GetUserWatchlistUseCase
import com.muabdz.home.presentation.viewparam.homeitem.HomeUiItem
import com.muabdz.shared.data.model.viewparam.MovieViewParam
import com.muabdz.shared.data.model.viewparam.UserViewParam
import com.muabdz.shared.delegates.AddOrRemoveWatchlistDelegates
import com.muabdz.shared.delegates.AddOrRemoveWatchlistDelegatesImpl
import com.muabdz.shared.domain.GetCurrentUserUseCase
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getHomeFeedsUseCase: GetHomeFeedsUseCase,
    private val getUserWatchlistUseCase: GetUserWatchlistUseCase,
    private val getCurrentUserUseCase: GetCurrentUserUseCase
) : ViewModel(), AddOrRemoveWatchlistDelegates by AddOrRemoveWatchlistDelegatesImpl() {

    val homeFeedsResult = MutableLiveData<ViewResource<List<HomeUiItem>>>()
    val watchlistResult = MutableLiveData<ViewResource<List<MovieViewParam>>>()
    val currentUserResult = MutableLiveData<ViewResource<UserViewParam>>()

    init {
        init(viewModelScope)
    }

    fun fetchHome() {
        viewModelScope.launch {
            getHomeFeedsUseCase().collect {
                homeFeedsResult.postValue(it)
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
    fun fetchWatchlist() {
        viewModelScope.launch {
            getUserWatchlistUseCase().collect {
                watchlistResult.postValue(it)
            }
        }
    }


}