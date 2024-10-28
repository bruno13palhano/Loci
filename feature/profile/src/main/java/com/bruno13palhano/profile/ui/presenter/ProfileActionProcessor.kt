package com.bruno13palhano.profile.ui.presenter

import com.bruno13palhano.ui.shared.ActionProcessor

internal class ProfileActionProcessor : ActionProcessor<ProfileAction, ProfileEvent> {
    override fun process(action: ProfileAction): ProfileEvent {
        return when (action) {
            is ProfileAction.OnRefresh -> ProfileEvent.Refresh
        }
    }
}