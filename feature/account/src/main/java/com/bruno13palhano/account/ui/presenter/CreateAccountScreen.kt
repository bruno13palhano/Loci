package com.bruno13palhano.account.ui.presenter

import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bruno13palhano.account.R
import com.bruno13palhano.account.ui.viewmodel.CreateAccountViewModel
import com.bruno13palhano.ui.components.CircularProgress
import com.bruno13palhano.ui.components.CustomPasswordTextField
import com.bruno13palhano.ui.components.CustomTextField
import com.bruno13palhano.ui.components.clickableWithoutRipple
import com.bruno13palhano.ui.components.rememberFlowWithLifecycle
import kotlinx.coroutines.launch

@Composable
internal fun CreateAccountRoute(
    modifier: Modifier = Modifier,
    navigateTo: (destination: CreateAccountDestination) -> Unit,
    viewModel: CreateAccountViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    val effects = rememberFlowWithLifecycle(viewModel.effects)

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val fillFieldsErrorMessage = stringResource(id = R.string.fill_all_fields)

    LaunchedEffect(effects) {
        effects.collect { effect ->
            when (effect) {
                is CreateAccountEffect.ShowError -> {
                    scope.launch {
                        snackbarHostState.showSnackbar(
                            message = fillFieldsErrorMessage,
                            withDismissAction = true
                        )
                    }
                }

                is CreateAccountEffect.NavigateTo -> navigateTo(effect.destination)
            }
        }
    }

    CreateAccountContent(
        modifier = modifier,
        snackbarHostState = snackbarHostState,
        state = state,
        onAction = viewModel::onAction
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CreateAccountContent(
    modifier: Modifier = Modifier,
    snackbarHostState: SnackbarHostState,
    state: CreateAccountState,
    onAction: (action: CreateAccountAction) -> Unit
) {
    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current

    Scaffold(
        modifier = modifier
            .clickableWithoutRipple {
                keyboardController?.hide()
                focusManager.clearFocus(force = true)
            }
            .consumeWindowInsets(WindowInsets.statusBars)
            .consumeWindowInsets(WindowInsets.safeDrawing),
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(id = R.string.create_account)) },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            onAction(
                                CreateAccountAction.OnNavigateTo(
                                    destination = CreateAccountDestination.Back
                                )
                            )
                        }
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(id = R.string.navigate_back)
                        )
                    }
                }
            )
        },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        floatingActionButton = {
            if (!state.loading) {
                FloatingActionButton(onClick = { onAction(CreateAccountAction.OnDone) }) {
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
                    value = state.createAccountFields.name,
                    onValueChange = state.createAccountFields::updateName,
                    isError = state.error && state.createAccountFields.name.isBlank(),
                    label = stringResource(id = R.string.name),
                    placeholder = stringResource(id = R.string.enter_name)
                )

                CustomTextField(
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .fillMaxWidth(),
                    value = state.createAccountFields.email,
                    onValueChange = state.createAccountFields::updateEmail,
                    isError = state.error && state.createAccountFields.email.isBlank(),
                    label = stringResource(id = R.string.email),
                    placeholder = stringResource(id = R.string.enter_email)
                )

                CustomPasswordTextField(
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .fillMaxWidth(),
                    visibility = state.passwordVisible,
                    value = state.createAccountFields.password,
                    isError = state.error && state.createAccountFields.password.isBlank(),
                    onValueChange = state.createAccountFields::updatePassword,
                    togglePasswordVisibility = {
                        onAction(CreateAccountAction.OnTogglePasswordVisibility)
                    },
                    label = stringResource(id = R.string.password),
                    placeholder = stringResource(id = R.string.enter_password)
                )

                CustomPasswordTextField(
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .fillMaxWidth(),
                    visibility = state.confirmPasswordVisible,
                    value = state.createAccountFields.confirmPassword,
                    isError = state.error && state.createAccountFields.confirmPassword.isBlank(),
                    onValueChange = state.createAccountFields::updateConfirmPassword,
                    togglePasswordVisibility = {
                        onAction(CreateAccountAction.OnToggleConfirmPasswordVisibility)
                    },
                    label = stringResource(id = R.string.confirm_password),
                    placeholder = stringResource(id = R.string.enter_confirm_password)
                )
            }
        }
    }
}

@Preview
@Composable
private fun CreateAccountPreview() {
    CreateAccountContent(
        snackbarHostState = SnackbarHostState(),
        state = CreateAccountState(
            loading = false,
            createAccountFields = CreateAccountFields().apply {
                updateName("username")
                updateEmail("user@email.com")
                updatePassword("12345678")
                updateConfirmPassword("12345678")
            },
            error = false,
            passwordVisible = true,
            confirmPasswordVisible = false
        ),
        onAction = {}
    )
}

@Preview
@Composable
private fun CreateAccountErrorPreview() {
    CreateAccountContent(
        snackbarHostState = SnackbarHostState(),
        state = CreateAccountState(
            loading = false,
            createAccountFields = CreateAccountFields().apply {
                updateName("username")
                updateEmail("user@email.com")
                updatePassword("12345678")
                updateConfirmPassword("")
            },
            error = true,
            passwordVisible = true,
            confirmPasswordVisible = true
        ),
        onAction = {}
    )
}

@Preview
@Composable
private fun CreateAccountLoadingPreview() {
    CreateAccountContent(
        snackbarHostState = SnackbarHostState(),
        state = CreateAccountState(
            loading = true,
            createAccountFields = CreateAccountFields(),
            error = false,
            passwordVisible = false,
            confirmPasswordVisible = false
        ),
        onAction = {}
    )
}