package com.varqulabs.dolarblue.auth.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.AuthCredential
import com.varqulabs.dolarblue.auth.data.useCases.SignInWithGoogleAccountUseCase
import com.varqulabs.dolarblue.auth.presentation.login.AuthEvent.Loading
import com.varqulabs.dolarblue.core.domain.DataState
import com.varqulabs.dolarblue.core.presentation.utils.mvi.MVIContract
import com.varqulabs.dolarblue.core.presentation.utils.mvi.mviDelegate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val signInWithGoogleAccountUseCase: SignInWithGoogleAccountUseCase,
) : ViewModel(), MVIContract<AuthState, AuthEvent, AuthUiEffect> by mviDelegate(AuthState()) {

    override fun eventHandler(event: AuthEvent) {
        when (event) {
            is Loading -> updateUi { copy(isLoading = isLoading) }
            is AuthEvent.OnClickDrawer -> emitOpenDrawer()
            is AuthEvent.OnClickSignInWithGoogle -> signInWithGoogleAccount(event.credential)
            AuthEvent.LoginMessageShown -> updateUi { copy(isLoading = false, loginMessage = "", isError = false, isLoggedSuccessful = false) }
        }
    }

    private fun emitOpenDrawer() = viewModelScope.emitEffect(AuthUiEffect.OpenDrawer)

    private fun signInWithGoogleAccount(credential: AuthCredential) = viewModelScope.launch {
        signInWithGoogleAccountUseCase.execute(credential).collectLatest {
            when(it){
                is DataState.Error -> updateUi { copy( loginMessage = "Error inesperado", isError = true, isLoggedSuccessful = false, isLoading = false) }
                DataState.Loading -> updateUi { copy(isLoading = true) }
                DataState.NetworkError -> updateUi { copy(loginMessage = "Error de conexion", isError = true, isLoading = false, isLoggedSuccessful = false) }
                is DataState.Success -> updateUi { copy(loginMessage = "Inicio de sesion exitoso", isLoggedSuccessful = true, isError = false, isLoading = false) }
            }
        }
    }

}