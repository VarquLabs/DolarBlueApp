package com.varqulabs.dolarblue.calculator.data.repository

import com.varqulabs.dolarblue.calculator.data.local.database.dao.ConversionDao
import com.varqulabs.dolarblue.calculator.domain.repository.ConversionRepository
import com.varqulabs.dolarblue.core.domain.mappers.mapToEntity
import com.varqulabs.dolarblue.core.domain.model.Conversion

class ConversionRepositoryImpl(
    private val conversionDao: ConversionDao
): ConversionRepository {
    override suspend fun insertConversion(conversion: Conversion) {
        conversionDao.insertConversion(conversion.mapToEntity())
    }
}