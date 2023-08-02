package com.muabdz.register.di

import com.muabdz.core.base.FeatureModules
import com.muabdz.register.data.network.datasource.RegisterDataSource
import com.muabdz.register.data.network.datasource.RegisterDataSourceImpl
import com.muabdz.register.data.network.services.RegisterFeatureApi
import com.muabdz.register.data.repository.RegisterRepository
import com.muabdz.register.data.repository.RegisterRepositoryImpl
import com.muabdz.register.domain.CheckRegisterFieldUseCase
import com.muabdz.register.domain.RegisterUserUseCase
import com.muabdz.register.presentation.ui.RegisterViewModel
import com.muabdz.shared.data.remote.NetworkClient
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.Module
import org.koin.dsl.module

object RegisterModules : FeatureModules {
    override val repositories: Module = module {
        single<RegisterRepository> { RegisterRepositoryImpl(get()) }
    }
    override val viewModels: Module = module {
        viewModelOf(::RegisterViewModel)
    }
    override val dataSources: Module = module {
        single<RegisterDataSource> { RegisterDataSourceImpl(get())}
    }
    override val useCases: Module = module {
        single { CheckRegisterFieldUseCase(Dispatchers.IO) }
        single { RegisterUserUseCase(get(), get(), get(), Dispatchers.IO) }
    }
    override val network: Module = module {
        single<RegisterFeatureApi> { get<NetworkClient>().create() }
    }

    override fun getModules(): List<Module> = listOf (
        repositories,
        viewModels,
        dataSources,
        useCases,
        network
    )
}