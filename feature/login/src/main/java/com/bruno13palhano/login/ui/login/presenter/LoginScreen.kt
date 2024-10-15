package com.bruno13palhano.login.ui.login.presenter

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bruno13palhano.login.R
import com.bruno13palhano.login.ui.login.viewmodel.LoginViewModel
import com.bruno13palhano.ui.components.clickableWithoutRipple
import com.bruno13palhano.ui.components.rememberFlowWithLifecycle
import kotlinx.coroutines.launch

@Composable
internal fun LoginRoute(
    modifier: Modifier = Modifier,
    navigateToHome: () -> Unit,
    navigateToNewAccount: () -> Unit,
    viewModel: LoginViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    val effects = rememberFlowWithLifecycle(flow = viewModel.effects)

    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val fillFieldsErrorMessage = stringResource(id = R.string.fill_all_fields)

    LaunchedEffect(effects) {
        effects.collect { effect ->
            when (effect) {
                is LoginEffect.ShowLoading -> {

                }

                is LoginEffect.ShowError -> {
                    scope.launch {
                        snackbarHostState.showSnackbar(
                            message = fillFieldsErrorMessage,
                            withDismissAction = true
                        )
                    }
                }

                is LoginEffect.NavigateToHome -> navigateToHome()

                is LoginEffect.NavigateToNewAccount -> navigateToNewAccount()
            }
        }
    }

    LoginContent(
        modifier = modifier.clickableWithoutRipple {
            keyboardController?.hide()
            focusManager.clearFocus(force = true)
        },
        snackbarHostState = snackbarHostState,
        state = state,
        onAction = viewModel::onAction
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun LoginContent(
    modifier: Modifier = Modifier,
    snackbarHostState: SnackbarHostState,
    state: LoginState,
    onAction: (action: LoginAction) -> Unit
) {
    Scaffold(
        modifier = modifier
            .consumeWindowInsets(WindowInsets.statusBars)
            .consumeWindowInsets(WindowInsets.safeDrawing),
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(id = R.string.login)) }
            )
        },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
//        floatingActionButton = {
//            FloatingActionButton(onClick = { onAction(LoginAction.OnLogin) }) {
//                Icon(
//                    imageVector = Icons.Filled.Done,
//                    contentDescription = stringResource(id = R.string.done)
//                )
//            }
//        }
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .consumeWindowInsets(it)
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .fillMaxWidth(),
                value = state.loginFields.email,
                onValueChange = state.loginFields::updateEmail,
                label = { Text(text = stringResource(id = R.string.email)) },
                placeholder = { Text(text = stringResource(id = R.string.enter_email)) },
                isError = state.error && state.loginFields.email.isBlank(),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(onDone = { defaultKeyboardAction(ImeAction.Done) })
            )

            OutlinedTextField(
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .fillMaxWidth(),
                value = state.loginFields.password,
                onValueChange = state.loginFields::updatePassword,
                label = { Text(text = stringResource(id = R.string.password)) },
                placeholder = { Text(text = stringResource(id = R.string.enter_password)) },
                isError = state.error && state.loginFields.password.isBlank(),
                trailingIcon = {
                    if (state.passwordVisible) {
                        IconButton(onClick = { onAction(LoginAction.OnTogglePasswordVisibility) }) {
                            Icon(
                                imageVector = Icons.Filled.Visibility,
                                contentDescription = null
                            )
                        }
                    } else {
                        IconButton(onClick = { onAction(LoginAction.OnTogglePasswordVisibility) }) {
                            Icon(
                                imageVector = Icons.Filled.VisibilityOff,
                                contentDescription = null
                            )
                        }
                    }
                },
                visualTransformation = if (state.passwordVisible) {
                    VisualTransformation.None
                } else {
                    PasswordVisualTransformation()
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(onDone = { defaultKeyboardAction(ImeAction.Done) })
            )

            Button(
                modifier = Modifier
                    .padding(32.dp)
                    .fillMaxWidth(),
                onClick = { /*TODO*/ }
            ) {
                Text(text = "Login")
            }

            Button(
                onClick = { /*TODO*/ }
            ) {
                Text(text = "Create Account")
            }
        }
    }
}

@Preview
@Composable
private fun LoginPreview() {
    LoginContent(
        snackbarHostState = SnackbarHostState(),
        state = LoginState(
            loading = false,
            loginFields = LoginFields().apply {
                updateEmail("")
                updatePassword("12345678")
            },
            error = true,
            passwordVisible = true
        )
    ) {}
}