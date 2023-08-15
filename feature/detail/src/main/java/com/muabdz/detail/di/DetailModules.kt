package com.muabdz.detail.di

import com.muabdz.core.base.FeatureModules
import com.muabdz.detail.data.network.datasource.DetailDataSource
import com.muabdz.detail.data.network.datasource.DetailDataSourceImpl
import com.muabdz.detail.data.network.service.DetailFeatureApi
import com.muabdz.detail.data.repository.DetailRepository
import com.muabdz.detail.data.repository.DetailRepositoryImpl
import com.muabdz.detail.domain.GetMovieDetailUseCase
import com.muabdz.shared.data.remote.NetworkClient
import kotlinx.coroutines.Dispatchers
import org.koin.core.module.Module
import org.koin.dsl.module

object DetailModules: FeatureModules {
    override val repositories: Module = module {
        single<DetailRepository> { DetailRepositoryImpl(get()) }
    }
    override val viewModels: Module = module {
        // TODO: implement later
    }
    override val dataSources: Module = module {
        single<DetailDataSource> { DetailDataSourceImpl(get()) }
    }
    override val useCases: Module = module {
        single { GetMovieDetailUseCase(get(), Dispatchers.IO) }
    }
    override val network: Module = module {
        single<DetailFeatureApi> { get<NetworkClient>().create() }
    }

    override fun getModules(): List<Module> = listOf(
        repositories,
        viewModels,
        dataSources,
        useCases,
        network
    )
}