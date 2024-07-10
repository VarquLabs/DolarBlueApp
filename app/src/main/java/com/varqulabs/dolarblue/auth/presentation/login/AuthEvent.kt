package com.varqulabs.dolarblue.auth.presentation.login

import com.google.firebase.auth.AuthCredential

sealed interface AuthEvent {

    data object OnClickDrawer : AuthEvent

    object LoginMessageShown : AuthEvent

    data class OnClickSignInWithGoogle( val credential: AuthCredential) : AuthEvent

    data class Loading(val isLoading: Boolean) : AuthEvent

}