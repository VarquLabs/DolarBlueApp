package com.varqulabs.dolarblue.calculator.domain.repository

import com.varqulabs.dolarblue.core.domain.model.Conversion

interface ConversionRepository {
    suspend fun insertConversion(conversion: Conversion)
}