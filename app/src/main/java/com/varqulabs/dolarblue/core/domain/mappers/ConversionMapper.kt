package com.varqulabs.dolarblue.core.domain.mappers

import com.varqulabs.dolarblue.core.data.local.database.entities.ConversionEntity
import com.varqulabs.dolarblue.core.domain.model.Conversion

fun Conversion.mapToEntity() = ConversionEntity(
    id = id,
    name = name,
    pesosBob = pesosBob,
    pesosArg = pesosArg,
    dollar = dollar,
    date = date,
    isFavorite = isFavorite,
    currentExchangeId = currentExchangeId
)