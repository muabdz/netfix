package com.muabdz.shared.di

import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.google.gson.Gson
import com.muabdz.core.base.BaseModule
import com.muabdz.shared.data.local.datastore.UserPreferenceDataSource
import com.muabdz.shared.data.local.datastore.UserPreferenceDataSourceImpl
import com.muabdz.shared.data.local.datastore.UserPreferenceFactory
import com.muabdz.shared.data.remote.NetworkClient
import com.muabdz.shared.data.remote.datasource.SharedFeatureApiDataSource
import com.muabdz.shared.data.remote.datasource.SharedFeatureApiDataSourceImpl
import com.muabdz.shared.data.remote.services.SharedFeatureApi
import com.muabdz.shared.data.repository.SharedApiRepository
import com.muabdz.shared.data.repository.SharedApiRepositoryImpl
import com.muabdz.shared.data.repository.UserPreferenceRepository
import com.muabdz.shared.data.repository.UserPreferenceRepositoryImpl
import com.muabdz.shared.domain.GetUserTokenUseCase
import com.muabdz.shared.domain.SaveAuthDataUseCase
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module

object SharedModules : BaseModule {
    override fun getModules(): List<Module> = listOf(
        remote,
        local,
        dataSource,
        repository,
        sharedUseCase,
        common
    )

    private val remote = module {
        single { ChuckerInterceptor.Builder(androidContext()).build() }
        single { NetworkClient(get(), get()) }
        single<SharedFeatureApi> { get<NetworkClient>().create() }
    }

    private val local = module {
        single { UserPreferenceFactory(androidContext()).create() }
    }

    private val dataSource = module {
        single<UserPreferenceDataSource> { UserPreferenceDataSourceImpl(get(), get())}
        single<SharedFeatureApiDataSource> {SharedFeatureApiDataSourceImpl(get())}
    }

    private val repository = module {
        single<UserPreferenceRepository> { UserPreferenceRepositoryImpl(get()) }
        single<SharedApiRepository> { SharedApiRepositoryImpl(get()) }
    }

    private val sharedUseCase = module {
        single { GetUserTokenUseCase(get(), Dispatchers.IO) }
        single { SaveAuthDataUseCase(get(), Dispatchers.IO) }
    }

    private val common = module {
        single { Gson() }
    }
}