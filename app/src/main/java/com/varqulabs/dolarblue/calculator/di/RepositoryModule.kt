package com.varqulabs.dolarblue.calculator.di

import com.varqulabs.dolarblue.calculator.data.local.database.dao.ConversionDao
import com.varqulabs.dolarblue.calculator.data.local.database.dao.CurrentExchangeRateDao
import com.varqulabs.dolarblue.calculator.data.remote.DollarBlueService
import com.varqulabs.dolarblue.calculator.data.repository.ConversionRepositoryImpl
import com.varqulabs.dolarblue.calculator.data.repository.CurrentExchangeRateRepositoryImpl
import com.varqulabs.dolarblue.calculator.data.repository.DollarBlueRepositoryImpl
import com.varqulabs.dolarblue.calculator.domain.repository.ConversionRepository
import com.varqulabs.dolarblue.calculator.domain.repository.CurrentExchangeRateRepository
import com.varqulabs.dolarblue.calculator.domain.repository.DollarBlueRepository
import com.varqulabs.dolarblue.calculator.domain.useCases.GetDollarBlueUseCase
import com.varqulabs.dolarblue.calculator.domain.useCases.InsertConversionUseCase
import com.varqulabs.dolarblue.calculator.domain.useCases.InsertExchangeRateUseCase
import com.varqulabs.dolarblue.core.di.IoDispatcher
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideDollarBlueRepository(service: DollarBlueService): DollarBlueRepository {
        return DollarBlueRepositoryImpl(service)
    }

    @Provides
    @Singleton
    fun provideGetDollarBlueUseCase(repository: DollarBlueRepository): GetDollarBlueUseCase {
        return GetDollarBlueUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideConversionRepository(
        conversionDao: ConversionDao
    ) : ConversionRepository {
        return ConversionRepositoryImpl(conversionDao)
    }

    @Provides
    @Singleton
    fun provideInsertConversionUseCase(
        @IoDispatcher dispatcher: CoroutineDispatcher,
        conversionRepository: ConversionRepository
    ): InsertConversionUseCase {
        return InsertConversionUseCase(
            dispatcher = dispatcher,
            conversionRepository = conversionRepository
        )
    }

    @Provides
    @Singleton
    fun provideCurrentExchangeRateRepository(
        currentExchangeRateDao: CurrentExchangeRateDao
    ) : CurrentExchangeRateRepository {
        return CurrentExchangeRateRepositoryImpl(currentExchangeRateDao)
    }

    @Provides
    @Singleton
    fun provideInsertExchangeRateUseCase(
        @IoDispatcher dispatcher: CoroutineDispatcher,
        currentExchangeRateRepository: CurrentExchangeRateRepository
    ): InsertExchangeRateUseCase {
        return InsertExchangeRateUseCase(
            dispatcher = dispatcher,
            currentExchangeRateRepository = currentExchangeRateRepository
        )
    }
}