package com.muabdz.detail.presentation.ui.movieinfo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muabdz.shared.delegates.AddOrRemoveWatchlistDelegates
import com.muabdz.shared.delegates.AddOrRemoveWatchlistDelegatesImpl

class InfoViewModel: ViewModel(),
    AddOrRemoveWatchlistDelegates by AddOrRemoveWatchlistDelegatesImpl() {

        init {
            init(viewModelScope)
        }
}