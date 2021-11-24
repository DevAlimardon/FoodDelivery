package com.example.fooddelivery.di


import com.example.fooddelivery.data.repository.AppRepository
import com.example.fooddelivery.data.repositoryImpl.AppRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    @Singleton
    fun getAppRepository(impl: AppRepositoryImpl): AppRepository

}