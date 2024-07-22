package com.varqulabs.dolarblue.calculator.domain.useCases

import com.varqulabs.dolarblue.calculator.domain.repository.ConversionRepository
import com.varqulabs.dolarblue.core.domain.useCases.UseCase
import com.varqulabs.dolarblue.core.domain.model.Conversion
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class InsertConversionUseCase(
    dispatcher: CoroutineDispatcher,
    private val conversionRepository: ConversionRepository
): UseCase<Conversion, Unit>(dispatcher) {
    override suspend fun executeData(input: Conversion): Flow<Unit> {
        return flow {
            conversionRepository.insertConversion(input)
            emit(Unit)
        }
    }
}
