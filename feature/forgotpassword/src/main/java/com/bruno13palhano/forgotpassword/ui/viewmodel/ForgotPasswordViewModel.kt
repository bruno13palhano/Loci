package com.bruno13palhano.forgotpassword.ui.viewmodel

import androidx.compose.runtime.Composable
import com.bruno13palhano.forgotpassword.ui.presenter.ForgotPasswordAction
import com.bruno13palhano.forgotpassword.ui.presenter.ForgotPasswordActionProcess
import com.bruno13palhano.forgotpassword.ui.presenter.ForgotPasswordEffect
import com.bruno13palhano.forgotpassword.ui.presenter.ForgotPasswordEvent
import com.bruno13palhano.forgotpassword.ui.presenter.ForgotPasswordReducer
import com.bruno13palhano.forgotpassword.ui.presenter.ForgotPasswordState
import com.bruno13palhano.forgotpassword.ui.presenter.forgotPasswordPresenter
import com.bruno13palhano.ui.shared.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
internal class ForgotPasswordViewModel @Inject constructor(

) : BaseViewModel<ForgotPasswordState, ForgotPasswordEvent, ForgotPasswordEffect, ForgotPasswordAction>(
    actionProcessor = ForgotPasswordActionProcess(),
    reducer = ForgotPasswordReducer()
) {
    @Composable
    override fun states(events: Flow<ForgotPasswordEvent>): ForgotPasswordState {
        return forgotPasswordPresenter(
            reducer = reducer,
            events = events,
            sendEvent = ::sendEvent,
            sendEffect = ::sendEffect
        )
    }
}