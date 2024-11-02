package com.bruno13palhano.login.ui.login.presenter

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
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
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bruno13palhano.login.R
import com.bruno13palhano.login.ui.login.viewmodel.LoginViewModel
import com.bruno13palhano.ui.components.CircularProgress
import com.bruno13palhano.ui.components.CustomPasswordTextField
import com.bruno13palhano.ui.components.CustomTextField
import com.bruno13palhano.ui.components.clickableWithoutRipple
import com.bruno13palhano.ui.components.rememberFlowWithLifecycle
import kotlinx.coroutines.launch

@Composable
internal fun LoginRoute(
    modifier: Modifier = Modifier,
    navigateTo: (destination: LoginDestination) -> Unit,
    viewModel: LoginViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    val effects = rememberFlowWithLifecycle(flow = viewModel.effects)

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val fillFieldsErrorMessage = stringResource(id = R.string.fill_all_fields)

    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current

    LaunchedEffect(effects) {
        effects.collect { effect ->
            when (effect) {
                is LoginEffect.ShowError -> {
                    scope.launch {
                        snackbarHostState.showSnackbar(
                            message = fillFieldsErrorMessage,
                            withDismissAction = true
                        )
                    }
                }

                is LoginEffect.DismissKeyboard -> {
                    clearFocusAndDismissKeyboard(
                        focusManager = focusManager,
                        keyboardController = keyboardController
                    )
                }

                is LoginEffect.NavigateTo -> {
                    clearFocusAndDismissKeyboard(
                        focusManager = focusManager,
                        keyboardController = keyboardController
                    )

                    navigateTo(effect.destination)
                }
            }
        }
    }

    LoginContent(
        modifier = modifier,
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
            .clickableWithoutRipple { onAction(LoginAction.OnDismissKeyboard) }
            .consumeWindowInsets(WindowInsets.statusBars)
            .consumeWindowInsets(WindowInsets.safeDrawing),
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(id = R.string.login)) }
            )
        },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) {
        if (state.loading) {
            CircularProgress(
                modifier = Modifier
                    .padding(it)
                    .consumeWindowInsets(it)
                    .fillMaxSize()
            )
        } else {
            Column(
                modifier = Modifier
                    .padding(it)
                    .consumeWindowInsets(it)
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CustomTextField(
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .fillMaxWidth(),
                    value = state.loginFields.email,
                    onValueChange = state.loginFields::updateEmail,
                    label = stringResource(id = R.string.email),
                    placeholder = stringResource(id = R.string.enter_email),
                    isError = state.error && state.loginFields.email.isBlank(),
                )

                CustomPasswordTextField(
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .fillMaxWidth(),
                    visibility = state.passwordVisible,
                    value = state.loginFields.password,
                    onValueChange = state.loginFields::updatePassword,
                    label = stringResource(id = R.string.password),
                    placeholder = stringResource(id = R.string.enter_password),
                    isError = state.error && state.loginFields.password.isBlank(),
                    togglePasswordVisibility = { onAction(LoginAction.OnTogglePasswordVisibility) }
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    TextButton(
                        onClick = {
                            onAction(
                                LoginAction.OnNavigateTo(
                                    destination = LoginDestination.ForgotPassword
                                )
                            )
                        }
                    ) {
                        Text(
                            text = stringResource(id = R.string.forgot_password),
                            textAlign = TextAlign.End,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }

                Button(
                    modifier = Modifier
                        .padding(start = 32.dp, top = 32.dp, bottom = 24.dp, end = 32.dp)
                        .fillMaxWidth(),
                    onClick = { onAction(LoginAction.OnLogin) }
                ) {
                    Text(text = stringResource(id = R.string.login))
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    HorizontalDivider(
                        modifier = Modifier
                            .padding(16.dp)
                            .weight(1f)
                    )
                    Text(
                        text = stringResource(id = R.string.or),
                        fontWeight = FontWeight.Medium
                    )
                    HorizontalDivider(
                        modifier = Modifier
                            .padding(16.dp)
                            .weight(1f)
                    )
                }

                Button(
                    modifier = Modifier.padding(top = 24.dp, bottom = 32.dp),
                    onClick = {
                        onAction(
                            LoginAction.OnNavigateTo(destination = LoginDestination.NewAccount)
                        )
                    }
                ) {
                    Text(text = stringResource(id = R.string.create_account))
                }
            }
        }
    }
}

private fun clearFocusAndDismissKeyboard(
    focusManager: FocusManager,
    keyboardController: SoftwareKeyboardController?
) {
    focusManager.clearFocus(force = true)
    keyboardController?.hide()
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

@Preview
@Composable
private fun LoginLoadingPreview() {
    LoginContent(
        snackbarHostState = SnackbarHostState(),
        state = LoginState(
            loading = true,
            loginFields = LoginFields().apply {
                updateEmail("")
                updatePassword("12345678")
            },
            error = true,
            passwordVisible = true
        )
    ) {}
}

@Preview(device = "spec:parent=pixel_5,orientation=landscape")
@Composable
private fun LoginLandscapePreview() {
    LoginContent(
        snackbarHostState = SnackbarHostState(),
        state = LoginState(
            loading = false,
            loginFields = LoginFields().apply {
                updateEmail("user@email.com")
                updatePassword("12345678")
            },
            error = false,
            passwordVisible = false
        )
    ) {}
}