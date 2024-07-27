package com.varqulabs.dolarblue.core.domain.model

import kotlinx.datetime.LocalDateTime

data class CurrentExchangeRate(
    val id: Int = 0,
    val pesosBob: String,
    val pesosArg: String,
    val date: LocalDateTime
)
