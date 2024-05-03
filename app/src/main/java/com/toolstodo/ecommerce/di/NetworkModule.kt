package com.toolstodo.ecommerce.di

import com.toolstodo.ecommerce.data.network.PlatziStoreApiClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val URL_API = "https://api.escuelajs.co/api/v1/"

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(URL_API)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun providePlatziApiClient(retrofit: Retrofit): PlatziStoreApiClient =
        retrofit.create(PlatziStoreApiClient::class.java)


}