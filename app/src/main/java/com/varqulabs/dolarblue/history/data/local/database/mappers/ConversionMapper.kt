package com.varqulabs.dolarblue.history.data.local.database.mappers

import com.varqulabs.dolarblue.core.data.local.database.entities.ConversionEntity
import com.varqulabs.dolarblue.core.domain.model.Conversion

fun ConversionEntity.mapToModel() = Conversion(
    id = id,
    name = name,
    pesosBob = pesosBob,
    pesosArg = pesosArg,
    dollar = dollar,
    date = date,
    isFavorite = isFavorite,
    currentExchangeId = currentExchangeId
)