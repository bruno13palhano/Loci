package com.bruno13palhano.login.ui.viewmodel

import androidx.compose.runtime.Composable
import com.bruno13palhano.login.ui.presenter.LoginAction
import com.bruno13palhano.login.ui.presenter.LoginActionProcessor
import com.bruno13palhano.login.ui.presenter.LoginEffect
import com.bruno13palhano.login.ui.presenter.LoginEvent
import com.bruno13palhano.login.ui.presenter.LoginFields
import com.bruno13palhano.login.ui.presenter.LoginReducer
import com.bruno13palhano.login.ui.presenter.LoginState
import com.bruno13palhano.login.ui.presenter.loginPresenter
import com.bruno13palhano.ui.shared.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
internal class LoginViewModel @Inject constructor(
    private val loginFields: LoginFields
) : BaseViewModel<LoginState, LoginEvent, LoginEffect, LoginAction>(
    actionProcessor = LoginActionProcessor(),
    reducer = LoginReducer()
) {
    @Composable
    override fun states(events: Flow<LoginEvent>): LoginState {
        return loginPresenter(
            loginFields = loginFields,
            reducer = reducer,
            events = events,
            sendEffect = ::sendEffect
        )
    }
}