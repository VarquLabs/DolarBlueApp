package com.varqulabs.dolarblue.history.data.local.database.mappers

import com.varqulabs.dolarblue.core.data.local.database.entities.CurrentExchangeRateEntity
import com.varqulabs.dolarblue.core.domain.model.CurrentExchangeRate

fun CurrentExchangeRateEntity.mapToModel() = CurrentExchangeRate(
    id = id,
    pesosBob = pesosBob,
    pesosArg = pesosArg,
    date = date
)
