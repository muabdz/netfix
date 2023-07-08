package com.muabdz.shared.data.remote

import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.muabdz.core.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

// TODO: prepare use case to get token
class NetworkClient(val chuckerInterceptor: ChuckerInterceptor) {

    inline fun <reified I> create(): I {

        val okHttpClient = OkHttpClient.Builder()
            // TODO: add auth interceptor
            .addInterceptor(chuckerInterceptor)
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

        return retrofit.create(I::class.java)
    }
}