package com.muabdz.shared.router

import android.content.Context
import android.content.Intent

interface ActivityRouter {
    fun loginActivity(context: Context): Intent
    fun homeActivity(context: Context): Intent
    fun registerActivity(context: Context): Intent
    fun detailActivity(context: Context, movieId: String): Intent
    fun playerActivity(context: Context, videoUrl: String): Intent
}