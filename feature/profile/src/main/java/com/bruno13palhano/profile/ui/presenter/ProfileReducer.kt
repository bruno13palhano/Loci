package com.bruno13palhano.profile.ui.presenter

import com.bruno13palhano.ui.shared.Reducer

internal class ProfileReducer : Reducer<ProfileState, ProfileEvent, ProfileEffect> {
    override fun reduce(
        previousState: ProfileState,
        event: ProfileEvent
    ): Pair<ProfileState, ProfileEffect?> {
        return when (event) {
            is ProfileEvent.Refresh -> previousState.copy(loading = true) to null
        }
    }
}