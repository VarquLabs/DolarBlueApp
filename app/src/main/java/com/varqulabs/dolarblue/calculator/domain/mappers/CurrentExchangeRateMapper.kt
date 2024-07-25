package com.varqulabs.dolarblue.calculator.domain.mappers

import com.varqulabs.dolarblue.core.data.local.database.entities.CurrentExchangeRateEntity
import com.varqulabs.dolarblue.core.domain.model.CurrentExchangeRate

fun CurrentExchangeRate.mapToEntity() = CurrentExchangeRateEntity(
    id = id,
    pesosBob = pesosBob,
    pesosArg = pesosArg,
    date = date
)