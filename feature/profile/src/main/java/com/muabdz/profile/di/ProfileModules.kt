package com.muabdz.profile.di

import com.muabdz.core.base.FeatureModules
import com.muabdz.profile.data.network.datasource.ProfileDataSource
import com.muabdz.profile.data.network.datasource.ProfileDataSourceImpl
import com.muabdz.profile.data.network.service.ProfileFeatureApi
import com.muabdz.profile.data.repository.ProfileRepository
import com.muabdz.profile.data.repository.ProfileRepositoryImpl
import com.muabdz.shared.data.remote.NetworkClient
import org.koin.core.module.Module
import org.koin.dsl.module

object ProfileModules: FeatureModules {
    override val repositories: Module = module {
        single<ProfileRepository> { ProfileRepositoryImpl(get()) }
    }
    override val viewModels: Module = module {
        // TODO: implement later
    }
    override val dataSources: Module = module {
        single<ProfileDataSource> { ProfileDataSourceImpl(get()) }
    }
    override val useCases: Module = module {
        // TODO: implement later
    }
    override val network: Module = module {
        single<ProfileFeatureApi> { get<NetworkClient>().create() }
    }

    override fun getModules(): List<Module> {
        return listOf(repositories, viewModels, dataSources, useCases, network)
    }
}