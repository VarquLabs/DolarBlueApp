package com.varqulabs.dolarblue.calculator.data.repository

import com.varqulabs.dolarblue.calculator.data.local.database.dao.CurrentExchangeRateDao
import com.varqulabs.dolarblue.calculator.domain.repository.CurrentExchangeRateRepository
import com.varqulabs.dolarblue.calculator.domain.mappers.mapToEntity
import com.varqulabs.dolarblue.core.domain.model.CurrentExchangeRate

class CurrentExchangeRateRepositoryImpl(
    private val currentExchangeRateDao: CurrentExchangeRateDao
): CurrentExchangeRateRepository {
    override suspend fun insertCurrentExchangeRate(currentExchangeRate: CurrentExchangeRate) {
        currentExchangeRateDao.insertCurrentExchangeRate(currentExchangeRate.mapToEntity())
    }
}