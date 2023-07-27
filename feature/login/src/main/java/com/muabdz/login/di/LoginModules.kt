package com.muabdz.login.di

import com.muabdz.core.base.FeatureModules
import com.muabdz.login.data.network.datasource.LoginDataSource
import com.muabdz.login.data.network.datasource.LoginDataSourceImpl
import com.muabdz.login.data.repository.LoginRepository
import com.muabdz.login.data.repository.LoginRepositoryImpl
import com.muabdz.login.data.service.LoginFeatureApi
import com.muabdz.login.domain.CheckLoginFieldUseCase
import com.muabdz.login.domain.LoginUserUseCase
import com.muabdz.login.presentation.ui.LoginViewModel
import com.muabdz.shared.data.remote.NetworkClient
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.Module
import org.koin.dsl.module

object LoginModules : FeatureModules {
    override val repositories: Module = module {
        single<LoginRepository> { LoginRepositoryImpl(get()) }
    }
    override val viewModels: Module = module {
        viewModelOf(::LoginViewModel)
    }
    override val dataSources: Module = module {
        single<LoginDataSource> { LoginDataSourceImpl(get()) }
    }
    override val useCases: Module = module {
        single { CheckLoginFieldUseCase(Dispatchers.IO) }
        single { LoginUserUseCase(get(), get(), get(), Dispatchers.IO) }
    }
    override val network: Module = module {
        single<LoginFeatureApi> { get<NetworkClient>().create() }
    }

    override fun getModules(): List<Module> = listOf(
        repositories,
        viewModels,
        dataSources,
        useCases,
        network
    )
}