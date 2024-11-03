package com.bruno13palhano.forgotpassword.ui.presenter

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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bruno13palhano.forgotpassword.R
import com.bruno13palhano.forgotpassword.ui.viewmodel.ForgotPasswordViewModel
import com.bruno13palhano.ui.components.CircularProgress
import com.bruno13palhano.ui.components.CustomPasswordTextField
import com.bruno13palhano.ui.components.CustomTextField
import com.bruno13palhano.ui.components.clearFocusAndDismissKeyboard
import com.bruno13palhano.ui.components.clickableWithoutRipple
import com.bruno13palhano.ui.components.rememberFlowWithLifecycle
import kotlinx.coroutines.launch

@Composable internal fun ForgotPasswordRoute(
    modifier: Modifier = Modifier,
    navigateTo: (destination: ForgotPasswordDestination) -> Unit,
    viewModel: ForgotPasswordViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    val effects = rememberFlowWithLifecycle(viewModel.effects)

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val errorMessages = getErrorMessagesFromStringsRes()

    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current

    LaunchedEffect(effects) {
        effects.collect { effect ->
            when (effect) {
                is ForgotPasswordEffect.ShowErrorInfo -> {
                    val errorMessage = setErrorMessage(
                        errorType = effect.errorType,
                        errorMessages = errorMessages
                    )

                    scope.launch {
                        snackbarHostState.showSnackbar(
                            message = errorMessage,
                            withDismissAction = true
                        )
                    }
                }

                is ForgotPasswordEffect.DismissKeyboard -> {
                    clearFocusAndDismissKeyboard(
                        focusManager = focusManager,
                        keyboardController = keyboardController
                    )
                }

                is ForgotPasswordEffect.NavigateTo -> {
                    clearFocusAndDismissKeyboard(
                        focusManager = focusManager,
                        keyboardController = keyboardController
                    )

                    navigateTo(effect.destination)
                }
            }
        }
    }

    ForgotPasswordContent(
        modifier = modifier,
        snackbarHostState = snackbarHostState,
        state = state,
        onAction = viewModel::onAction
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ForgotPasswordContent(
    modifier: Modifier = Modifier,
    snackbarHostState: SnackbarHostState,
    state: ForgotPasswordState,
    onAction: (action: ForgotPasswordAction) -> Unit
) {
    Scaffold(
        modifier = modifier
            .clickableWithoutRipple { onAction(ForgotPasswordAction.OnDismissKeyboard) }
            .consumeWindowInsets(WindowInsets.statusBars)
            .consumeWindowInsets(WindowInsets.safeDrawing),
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(id = R.string.forgot_password_title)) },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            onAction(
                                ForgotPasswordAction.OnNavigateTo(
                                    destination = ForgotPasswordDestination.Back
                                )
                            )
                        }
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = null
                        )
                    }
                }
            )
        },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        floatingActionButton = {
            if (!state.loading) {
                FloatingActionButton(
                    onClick = {
                        onAction(ForgotPasswordAction.OnVerifyEmail)
                    }
                ) {
                    Icon(
                        imageVector = Icons.Filled.Done,
                        contentDescription = null
                    )
                }
            }
        }
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
            ) {
                CustomTextField(
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .fillMaxWidth(),
                    value = state.forgotPasswordFields.email,
                    onValueChange = state.forgotPasswordFields::updateEmail,
                    label = stringResource(id = R.string.email),
                    placeholder = stringResource(id = R.string.enter_email)
                )

                if (state.emailVerified) {
                    ElevatedCard(
                        modifier = Modifier
                            .padding(16.dp)
                            .align(Alignment.CenterHorizontally)
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            CustomPasswordTextField(
                                modifier = Modifier
                                    .padding(horizontal = 8.dp)
                                    .fillMaxWidth(),
                                visibility = state.passwordVisible,
                                value = state.forgotPasswordFields.password,
                                onValueChange = state.forgotPasswordFields::updatePassword,
                                togglePasswordVisibility = {
                                    onAction(ForgotPasswordAction.OnTogglePasswordVisibility)
                                },
                                label = stringResource(id = R.string.password),
                                placeholder = stringResource(id = R.string.enter_password)
                            )

                            CustomPasswordTextField(
                                modifier = Modifier
                                    .padding(horizontal = 8.dp)
                                    .fillMaxWidth(),
                                visibility = state.confirmPasswordVisible,
                                value = state.forgotPasswordFields.confirmPassword,
                                onValueChange = state.forgotPasswordFields::updateConfirmPassword,
                                togglePasswordVisibility = {
                                    onAction(ForgotPasswordAction.OnToggleConfirmPasswordVisibility)
                                },
                                label = stringResource(id = R.string.confirm_password),
                                placeholder = stringResource(id = R.string.enter_confirm_password)
                            )

                            Row(
                                modifier = Modifier
                                    .padding(8.dp)
                                    .align(Alignment.End),
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                Button(
                                    onClick = {
                                        onAction(ForgotPasswordAction.OnCancelUpdatePassword)
                                    }
                                ) {
                                    Text(text = stringResource(id = R.string.cancel))
                                }

                                Button(
                                    onClick = { onAction(ForgotPasswordAction.OnUpdatePassword) }
                                ) {
                                    Text(text = stringResource(id = R.string.ok))
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun getErrorMessagesFromStringsRes(): List<String> {
    return listOf(
        stringResource(id = R.string.network_error),
        stringResource(id = R.string.fill_missing_field),
        stringResource(id = R.string.generic_error),
        stringResource(id = R.string.invalid_email),
        stringResource(id = R.string.password_does_not_match)
    )
}

// Map error type to error message
private fun setErrorMessage(errorType: ErrorType, errorMessages: List<String>): String {
    return when (errorType) {
        is ErrorType.NetworkError -> errorMessages[0]
        is ErrorType.FillMissingField -> errorMessages[1]
        is ErrorType.GenericError -> errorMessages[2]
        is ErrorType.InvalidEmail -> errorMessages[3]
        is ErrorType.PasswordDoesNotMatch -> errorMessages[4]
    }
}

@Preview
@Composable
private fun ForgotPasswordPreview() {
    ForgotPasswordContent(
        snackbarHostState = SnackbarHostState(),
        state = ForgotPasswordState(
            loading = false,
            emailVerified = false,
            forgotPasswordFields = ForgotPasswordFields(),
            passwordVisible = false,
            confirmPasswordVisible = false,
            invalidFields = false,
            internalError = false,
            showErrorInfo = false
        ),
        onAction = {}
    )
}

@Preview
@Composable
private fun ForgotPasswordEmailVerifiedPreview() {
    ForgotPasswordContent(
        snackbarHostState = SnackbarHostState(),
        state = ForgotPasswordState(
            loading = false,
            emailVerified = true,
            forgotPasswordFields = ForgotPasswordFields(),
            passwordVisible = false,
            confirmPasswordVisible = false,
            invalidFields = false,
            internalError = false,
            showErrorInfo = false
        ),
        onAction = {}
    )
}