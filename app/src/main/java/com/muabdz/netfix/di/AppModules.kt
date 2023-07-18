package com.muabdz.netfix.di

import com.muabdz.core.base.BaseModule
import com.muabdz.netfix.router.ActivityRouterImpl
import com.muabdz.shared.router.ActivityRouter
import org.koin.core.module.Module
import org.koin.dsl.module

object AppModules : BaseModule {
    override fun getModules(): List<Module> = listOf()

    val routers: Module = module {
        single<ActivityRouter> { ActivityRouterImpl() }
    }
}