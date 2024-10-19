package com.bruno13palhano.account.ui.viewmodel

import androidx.compose.runtime.Composable
import com.bruno13palhano.account.ui.presenter.CreateAccountAction
import com.bruno13palhano.account.ui.presenter.CreateAccountActionProcessor
import com.bruno13palhano.account.ui.presenter.CreateAccountEffect
import com.bruno13palhano.account.ui.presenter.CreateAccountEvent
import com.bruno13palhano.account.ui.presenter.CreateAccountReducer
import com.bruno13palhano.account.ui.presenter.CreateAccountState
import com.bruno13palhano.account.ui.presenter.createAccountPresenter
import com.bruno13palhano.ui.shared.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
internal class CreateAccountViewModel @Inject constructor(

) : BaseViewModel<CreateAccountState, CreateAccountEvent, CreateAccountEffect, CreateAccountAction>(
    actionProcessor = CreateAccountActionProcessor(),
    reducer = CreateAccountReducer()
) {
    @Composable
    override fun states(events: Flow<CreateAccountEvent>): CreateAccountState {
        return createAccountPresenter(
            reducer = reducer,
            events = events,
            sendEvent = ::sendEvent,
            sendEffect = ::sendEffect
        )
    }
}