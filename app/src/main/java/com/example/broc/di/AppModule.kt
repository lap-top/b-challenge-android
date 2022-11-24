package com.example.broc.di

import android.content.Context
import com.example.broc.common.Constants
import com.example.broc.data.remote.BroccoliApi
import com.example.broc.data.repository.DataStoreRepoImpl
import com.example.broc.data.repository.MailRepositoryImpl
import com.example.broc.domain.repository.DataStoreRepo
import com.example.broc.domain.repository.MailRepository
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

// Dependency Inject with dagger + hilt
@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    // Inform hilt how to create instance of Broccoli API
    @Provides
    @Singleton
    fun provideBroccoliApi(): BroccoliApi {
        val gson = GsonBuilder()
            .setLenient()
            .create()

        return Retrofit.Builder().baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(BroccoliApi::class.java)
    }


    // Inform hilt how to create mailRepository
    @Provides
    @Singleton
    fun provideMailRepository(api: BroccoliApi): MailRepository {
        return MailRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun providesDataStoreRepo(@ApplicationContext context: Context): DataStoreRepo {
        return DataStoreRepoImpl(context)
    }

}