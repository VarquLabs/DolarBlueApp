package com.varqulabs.dolarblue.auth.presentation.login

import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.GoogleAuthProvider
import com.varqulabs.dolarblue.R
import com.varqulabs.dolarblue.core.presentation.generics.top_bars.DrawerAppBar


@Composable
fun LoginScreen(
    state: AuthState,
    eventHandler: (AuthEvent) -> Unit
) {
    val context = LocalContext.current
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) {
        val task = GoogleSignIn.getSignedInAccountFromIntent(it.data)
        val account = task.getResult(ApiException::class.java)
        val credential = GoogleAuthProvider.getCredential(
            account.idToken,
            null
        )
        eventHandler(AuthEvent.OnClickSignInWithGoogle(credential))
    }
    LaunchedEffect(state.isLoading) { eventHandler(AuthEvent.Loading(state.isLoading)) }

    LaunchedEffect(key1 = state.isError, key2 = state.isLoggedSuccessful) {
        if (state.isError||state.isLoggedSuccessful){
            Toast.makeText(
                context,
                state.loginMessage,
                Toast.LENGTH_SHORT
            ).show()
            eventHandler(AuthEvent.LoginMessageShown)
        }
    }

    Scaffold(
        topBar = {
            DrawerAppBar(
                title = stringResource(id = R.string.copy_login),
                onClickDrawer = { eventHandler(AuthEvent.OnClickDrawer) }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(
                12.dp,
                Alignment.CenterVertically
            ),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            Button(onClick = {
                val options = GoogleSignInOptions.Builder(
                    GoogleSignInOptions.DEFAULT_SIGN_IN
                ).requestIdToken(context.getString(R.string.web_client_id))
                    .requestEmail().build()
                val client = GoogleSignIn.getClient(
                    context,
                    options
                )
                launcher.launch(client.signInIntent)
            }) {
                Text(text = "Iniciar sesion con Google")
            }

        }
    }
}