package com.muabdz.splashscreen.di

import com.muabdz.core.base.FeatureModules
import com.muabdz.shared.data.remote.NetworkClient
import com.muabdz.splashscreen.data.network.datasource.SplashScreenDataSource
import com.muabdz.splashscreen.data.network.datasource.SplashScreenDataSourceImpl
import com.muabdz.splashscreen.data.network.service.SplashScreenFeatureApi
import com.muabdz.splashscreen.data.repository.SplashScreenRepository
import com.muabdz.splashscreen.data.repository.SplashScreenRepositoryImpl
import com.muabdz.splashscreen.domain.SyncUserUseCase
import com.muabdz.splashscreen.presentation.SplashScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.Module
import org.koin.dsl.module

object SplashScreenModule : FeatureModules{
    override val repositories: Module = module {
        single<SplashScreenRepository> { SplashScreenRepositoryImpl(get()) }
    }
    override val viewModels: Module = module {
        viewModelOf(::SplashScreenViewModel)
    }
    override val dataSources: Module = module {
        single<SplashScreenDataSource> { SplashScreenDataSourceImpl(get()) }
    }
    override val useCases: Module = module {
        single { SyncUserUseCase(get(), get(), get()) }
    }
    override val network: Module = module {
        single<SplashScreenFeatureApi> { get<NetworkClient>().create() }
    }

    override fun getModules(): List<Module> = listOf(
        repositories,
        viewModels,
        dataSources,
        useCases,
        network
    )
}