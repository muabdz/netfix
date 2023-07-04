package com.muabdz.core.utils

import android.content.Context
import com.muabdz.core.exception.ApiErrorException
import com.muabdz.core.exception.NoInternetConnectionException
import com.muabdz.netfix.R
import java.lang.Exception

fun Context.getErrorMessage(exception: Exception) : String {
    return when(exception) {
        is NoInternetConnectionException -> getString(R.string.message_error_no_internet)
        is ApiErrorException -> exception.message.orEmpty()
        else -> getString(R.string.message_error_unknown)
    }
}