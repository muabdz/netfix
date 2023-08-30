package com.muabdz.netfix.di

import com.muabdz.core.base.BaseModule
import com.muabdz.netfix.router.ActivityRouterImpl
import com.muabdz.netfix.router.BottomSheetRouterImpl
import com.muabdz.netfix.router.FragmentRouterImpl
import com.muabdz.shared.router.ActivityRouter
import com.muabdz.shared.router.BottomSheetRouter
import com.muabdz.shared.router.FragmentRouter
import org.koin.core.module.Module
import org.koin.dsl.module

object AppModules : BaseModule {
    override fun getModules(): List<Module> = listOf(routers)

    val routers: Module = module {
        single<ActivityRouter> { ActivityRouterImpl() }
        single<BottomSheetRouter> { BottomSheetRouterImpl() }
        single<FragmentRouter> { FragmentRouterImpl() }
    }
}