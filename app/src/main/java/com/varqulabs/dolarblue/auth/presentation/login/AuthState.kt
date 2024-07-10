package com.varqulabs.dolarblue.auth.presentation.login

import androidx.compose.runtime.Stable

@Stable
data class AuthState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val isLoggedSuccessful: Boolean = false,
    val loginMessage: String = ""
)