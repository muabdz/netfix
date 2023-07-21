package com.muabdz.netfix

import android.app.Application
import com.muabdz.login.di.LoginModules
import com.muabdz.netfix.di.AppModules
import com.muabdz.shared.di.SharedModules
import com.muabdz.splashscreen.di.SplashScreenModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(
                AppModules.getModules() +
                SharedModules.getModules() +
                SplashScreenModules.getModules() +
                LoginModules.getModules()
            )
        }
    }
}