package com.varqulabs.dolarblue.core.domain.model

import kotlinx.datetime.LocalDateTime

data class Conversion(
    val id: Int = 0,
    val name: String,
    val pesosBob: String,
    val pesosArg: String,
    val dollar: String,
    val date: LocalDateTime,
    val isFavorite: Boolean,
    val currentExchangeId: Int
)
