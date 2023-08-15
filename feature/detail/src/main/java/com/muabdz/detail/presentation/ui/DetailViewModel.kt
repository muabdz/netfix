package com.muabdz.detail.presentation.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muabdz.core.wrapper.ViewResource
import com.muabdz.detail.domain.GetMovieDetailUseCase
import com.muabdz.shared.data.model.viewparam.MovieViewParam
import com.muabdz.shared.delegates.AddOrRemoveWatchlistDelegates
import com.muabdz.shared.delegates.AddOrRemoveWatchlistDelegatesImpl
import kotlinx.coroutines.launch

class DetailViewModel(
    private val getMovieDetailUseCase: GetMovieDetailUseCase
): ViewModel(), AddOrRemoveWatchlistDelegates by AddOrRemoveWatchlistDelegatesImpl() {

    val movieDetailResult = MutableLiveData<ViewResource<MovieViewParam>>()

    init {
        init(viewModelScope)
    }

    fun fetchMovieDetail(movieId: String) {
        viewModelScope.launch {
            getMovieDetailUseCase().collect {
                movieDetailResult.postValue(it)
            }
        }
    }
}