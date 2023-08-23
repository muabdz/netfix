package com.muabdz.netfix.router

import android.content.Context
import android.content.Intent
import com.muabdz.detail.presentation.ui.moviedetail.MovieDetailActivity
import com.muabdz.home.presentation.ui.home.HomeActivity
import com.muabdz.login.presentation.ui.LoginActivity
import com.muabdz.register.presentation.ui.RegisterActivity
import com.muabdz.shared.router.ActivityRouter

class ActivityRouterImpl: ActivityRouter {
    override fun loginActivity(context: Context): Intent {
        return Intent(context, LoginActivity::class.java)
    }

    override fun homeActivity(context: Context): Intent {
        return Intent(context, HomeActivity::class.java)
    }

    override fun registerActivity(context: Context): Intent {
        return Intent(context, RegisterActivity::class.java)
    }

    override fun detailActivity(context: Context, movieId: String): Intent {
        return MovieDetailActivity.createIntent(context, movieId)
    }
}