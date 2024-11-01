package com.bruno13palhano.login.ui.login.viewmodel

import androidx.compose.runtime.Composable
import com.bruno13palhano.data.di.UserRep
import com.bruno13palhano.data.user.UserRepository
import com.bruno13palhano.login.ui.login.presenter.LoginAction
import com.bruno13palhano.login.ui.login.presenter.LoginActionProcessor
import com.bruno13palhano.login.ui.login.presenter.LoginEffect
import com.bruno13palhano.login.ui.login.presenter.LoginEvent
import com.bruno13palhano.login.ui.login.presenter.LoginReducer
import com.bruno13palhano.login.ui.login.presenter.LoginState
import com.bruno13palhano.login.ui.login.presenter.loginPresenter
import com.bruno13palhano.ui.shared.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
internal class LoginViewModel @Inject constructor(
    @UserRep private val userRepository: UserRepository
) : BaseViewModel<LoginState, LoginEvent, LoginEffect, LoginAction>(
    actionProcessor = LoginActionProcessor(),
    reducer = LoginReducer()
) {
    @Composable
    override fun states(events: Flow<LoginEvent>): LoginState {
        return loginPresenter(
            repository = userRepository,
            reducer = reducer,
            events = events,
            sendEvent = ::sendEvent,
            sendEffect = ::sendEffect
        )
    }
}