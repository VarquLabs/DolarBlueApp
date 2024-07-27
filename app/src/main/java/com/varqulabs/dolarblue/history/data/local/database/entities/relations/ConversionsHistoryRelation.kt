package com.varqulabs.dolarblue.history.data.local.database.entities.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.varqulabs.dolarblue.core.data.local.database.entities.ConversionEntity
import com.varqulabs.dolarblue.core.data.local.database.entities.CurrentExchangeRateEntity

data class ConversionsHistoryRelation(
    @Embedded
    val currentExchangeRate: CurrentExchangeRateEntity,
    @Relation(
        entity = ConversionEntity::class,
        parentColumn = "id",
        entityColumn = "currentExchangeId"
    )
    val conversions: List<ConversionEntity>
)