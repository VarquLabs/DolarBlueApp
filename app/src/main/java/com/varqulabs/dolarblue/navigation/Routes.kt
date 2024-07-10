package com.varqulabs.dolarblue.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class Routes {

    @Serializable
    data object Home : Routes()

    @Serializable
    data object Calculator : Routes()

    @Serializable
    data object History : Routes()

    @Serializable
    data object Settings : Routes()
    @Serializable
    data object Login : Routes()

}