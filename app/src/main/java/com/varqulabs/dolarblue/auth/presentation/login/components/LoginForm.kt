package com.varqulabs.dolarblue.auth.presentation.login.components

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.GoogleAuthProvider
import com.varqulabs.dolarblue.R
import com.varqulabs.dolarblue.auth.presentation.login.LoginEvent
import com.varqulabs.dolarblue.auth.presentation.login.LoginState
import com.varqulabs.dolarblue.core.presentation.desingsystem.components.DolarBlueActionButton
import com.varqulabs.dolarblue.core.presentation.desingsystem.components.DolarBluePasswordTextField
import com.varqulabs.dolarblue.core.presentation.desingsystem.components.DolarBlueTextField

@Composable
fun LoginForm(
    modifier: Modifier = Modifier,
    context: Context,
    state: LoginState,
    eventHandler: (LoginEvent) -> Unit
) {

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) {
        if (it.resultCode == Activity.RESULT_OK){
            val task = GoogleSignIn.getSignedInAccountFromIntent(it.data)
            val account = task.getResult(ApiException::class.java)
            val credential = GoogleAuthProvider.getCredential(account.idToken, null)
            eventHandler(LoginEvent.OnClickLoginWithGoogle(credential))
        }
    }

    val focusManager = LocalFocusManager.current

    Column(
        modifier = modifier
    ) {

        DolarBlueTextField(
            state = state.email,
            error = state.emailError?.asString(context),
            startIcon = null,
            endIcon = null,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = {
                    focusManager.moveFocus(FocusDirection.Next)
                }
            ),
            onTextChange = {
                eventHandler(LoginEvent.OnEmailChange(it))
            },
            title = stringResource(R.string.title_email_field)
        )

        Spacer(modifier = Modifier.height(20.dp))

        DolarBluePasswordTextField(
            state = state.password,
            startIcon = null,
            isClickableText = {

            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    focusManager.clearFocus()
                }
            ),
            error = state.passwordError?.asString(context),
            additionalInfo = stringResource(R.string.text_forgot_password),
            onTextChange = {
                eventHandler(LoginEvent.OnPasswordChange(it))
            },
            title = stringResource(R.string.title_password_field)
        )

        Spacer(modifier = Modifier.height(20.dp))

        DolarBlueActionButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp),
            onClick = {
                eventHandler(LoginEvent.OnClickLogin)
            }
        ){
            Text(
                text = stringResource(R.string.text_button_login),
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.ExtraBold,
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        DolarBlueActionButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            onClick = {
                launcher.launch(getSignupWithGoogleAccountIntent(context))
            }
        ){
            Image(
                painter = painterResource(id = R.drawable.eliminar),
                contentDescription = "Google Logo",
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = stringResource(id = R.string.text_button_login_with_google),
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.ExtraBold
            )
        }
    }

}
private fun getSignupWithGoogleAccountIntent(context: Context): Intent {
    val options = GoogleSignInOptions.Builder(
        GoogleSignInOptions.DEFAULT_SIGN_IN
        ).requestIdToken(context.getString(R.string.web_client_id))
        .requestEmail().build()
    val client = GoogleSignIn.getClient(
        context,
        options
    )
    client.signOut()
    return client.signInIntent
}