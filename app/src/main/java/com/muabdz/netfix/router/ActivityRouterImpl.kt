package com.muabdz.netfix.router

import android.content.Context
import android.content.Intent
import com.muabdz.home.presentation.ui.HomeActivity
import com.muabdz.login.presentation.ui.LoginActivity
import com.muabdz.shared.router.ActivityRouter

class ActivityRouterImpl: ActivityRouter {
    override fun loginActivity(context: Context): Intent {
        return Intent(context, LoginActivity::class.java)
    }

    override fun homeActivity(context: Context): Intent {
        return Intent(context, HomeActivity::class.java)
    }
}