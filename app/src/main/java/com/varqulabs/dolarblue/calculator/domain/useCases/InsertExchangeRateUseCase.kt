package com.varqulabs.dolarblue.calculator.domain.useCases

import com.varqulabs.dolarblue.calculator.domain.repository.CurrentExchangeRateRepository
import com.varqulabs.dolarblue.core.domain.useCases.UseCase
import com.varqulabs.dolarblue.core.domain.model.CurrentExchangeRate
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class InsertExchangeRateUseCase (
    dispatcher: CoroutineDispatcher,
    private val currentExchangeRateRepository: CurrentExchangeRateRepository
): UseCase<CurrentExchangeRate, Unit>(dispatcher) {
    override suspend fun executeData(input: CurrentExchangeRate): Flow<Unit> {
        return flow {
            currentExchangeRateRepository.insertCurrentExchangeRate(input)
            emit(Unit)
        }
    }
}