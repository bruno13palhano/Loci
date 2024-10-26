package com.bruno13palhano.workspace.ui.presenter

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bruno13palhano.ui.components.CircularProgress
import com.bruno13palhano.ui.components.rememberFlowWithLifecycle
import com.bruno13palhano.workspace.R
import com.bruno13palhano.workspace.ui.viewmodel.WorkspaceViewModel
import kotlinx.coroutines.launch

@Composable
internal fun WorkspaceRoute(
    modifier: Modifier = Modifier,
    viewModel: WorkspaceViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    val effects = rememberFlowWithLifecycle(flow = viewModel.effects)

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    LaunchedEffect(effects) {
        effects.collect { effect ->
            when (effect) {
                is WorkspaceEffect.ShowErrorInfo -> {
                    scope.launch {
                        snackbarHostState.showSnackbar(
                            message = "Some error",
                            withDismissAction = true
                        )
                    }
                }
            }
        }
    }

    WorkspaceContent(
        modifier = modifier,
        snackbarHostState = snackbarHostState,
        state = state,
        onAction = viewModel::onAction
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun WorkspaceContent(
    modifier: Modifier = Modifier,
    snackbarHostState: SnackbarHostState,
    state: WorkspaceState,
    onAction: (action: WorkspaceAction) -> Unit
) {
    Scaffold(
        modifier = modifier
            .consumeWindowInsets(WindowInsets.statusBars)
            .consumeWindowInsets(WindowInsets.safeDrawing),
        topBar = { TopAppBar(title = { Text(text = stringResource(id = R.string.workspace)) }) },
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
            LazyColumn(
                modifier = Modifier
                    .consumeWindowInsets(it),
                contentPadding = PaddingValues(
                    vertical = 4.dp + it.calculateTopPadding(),
                    horizontal = 4.dp + it.calculateStartPadding(LayoutDirection.Ltr),
                )
            ) {

            }
        }
    }
}

@Preview
@Composable
fun WorkspacePreview() {
    WorkspaceContent(
        snackbarHostState = SnackbarHostState(),
        state = WorkspaceState.Initial,
        onAction = {}
    )
}