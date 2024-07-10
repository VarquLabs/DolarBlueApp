package com.varqulabs.dolarblue.auth.presentation.login

sealed class AuthUiEffect {

    data class ShowError(val message: String) : AuthUiEffect()

    data object OpenDrawer : AuthUiEffect()

}