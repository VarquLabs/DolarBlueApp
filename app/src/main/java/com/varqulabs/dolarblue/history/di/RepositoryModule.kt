package com.varqulabs.dolarblue.history.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    /** Fun base de prueba (ignorar) */
    @Provides
    @Singleton
    fun provideConversionRepository(){}
}