package com.muabdz.home.di

import com.muabdz.core.base.FeatureModules
import com.muabdz.home.data.network.datasource.HomeDataSource
import com.muabdz.home.data.network.datasource.HomeDataSourceImpl
import com.muabdz.home.data.network.service.HomeFeatureApi
import com.muabdz.home.data.repository.HomeRepository
import com.muabdz.home.data.repository.HomeRepositoryImpl
import com.muabdz.home.domain.GetHomeFeedsUseCase
import com.muabdz.home.domain.GetUserWatchlistUseCase
import com.muabdz.shared.data.remote.NetworkClient
import kotlinx.coroutines.Dispatchers
import org.koin.core.module.Module
import org.koin.dsl.module

object HomeModules: FeatureModules {
    override val repositories: Module = module {
        single<HomeRepository> { HomeRepositoryImpl(get()) }
    }
    override val viewModels: Module = module {
        // TODO: implement later
    }
    override val dataSources: Module = module {
        single<HomeDataSource> { HomeDataSourceImpl(get()) }
    }
    override val useCases: Module = module {
        single { GetHomeFeedsUseCase(get(), Dispatchers.IO) }
        single { GetUserWatchlistUseCase(get(), Dispatchers.IO) }
    }
    override val network: Module = module {
        single<HomeFeatureApi> { get<NetworkClient>().create() }
    }

    override fun getModules(): List<Module> = listOf(
        repositories,
        viewModels,
        dataSources,
        useCases,
        network
    )
}