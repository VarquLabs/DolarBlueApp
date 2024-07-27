package com.varqulabs.dolarblue.history.domain.model

import androidx.compose.runtime.Stable
import com.varqulabs.dolarblue.core.domain.model.Conversion
import com.varqulabs.dolarblue.core.domain.model.CurrentExchangeRate
import kotlinx.uuid.generateUUID
import kotlin.random.Random
import kotlinx.uuid.UUID


@Stable
data class ConversionsHistory(
    val id: String = UUID.generateUUID(Random).toString(),
    val currentExchangeRate: CurrentExchangeRate,
    val conversions: List<Conversion>
)
