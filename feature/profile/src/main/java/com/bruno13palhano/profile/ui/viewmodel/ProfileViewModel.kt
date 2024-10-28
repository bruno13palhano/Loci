package com.bruno13palhano.profile.ui.viewmodel

import androidx.compose.runtime.Composable
import com.bruno13palhano.profile.ui.presenter.ProfileAction
import com.bruno13palhano.profile.ui.presenter.ProfileActionProcessor
import com.bruno13palhano.profile.ui.presenter.ProfileEffect
import com.bruno13palhano.profile.ui.presenter.ProfileEvent
import com.bruno13palhano.profile.ui.presenter.ProfileReducer
import com.bruno13palhano.profile.ui.presenter.ProfileState
import com.bruno13palhano.profile.ui.presenter.profilePresenter
import com.bruno13palhano.ui.shared.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
internal class ProfileViewModel @Inject constructor(

) : BaseViewModel<ProfileState, ProfileEvent, ProfileEffect, ProfileAction>(
    actionProcessor = ProfileActionProcessor(),
    reducer = ProfileReducer()
) {
    @Composable
    override fun states(events: Flow<ProfileEvent>): ProfileState {
        return profilePresenter(
            reducer = reducer,
            events = events,
            sendEvent = ::sendEvent,
            sendEffect = ::sendEffect
        )
    }
}