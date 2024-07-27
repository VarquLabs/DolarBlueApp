package com.varqulabs.dolarblue.calculator.domain.repository

import com.varqulabs.dolarblue.core.domain.model.CurrentExchangeRate

interface CurrentExchangeRateRepository {
    suspend fun insertCurrentExchangeRate(currentExchangeRate: CurrentExchangeRate)
}