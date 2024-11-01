package com.bruno13palhano.home.ui.presenter

import com.bruno13palhano.ui.shared.ActionProcessor

internal class HomeActionProcessor : ActionProcessor<HomeAction, HomeEvent> {
    override fun process(action: HomeAction): HomeEvent {
        return when (action) {
            HomeAction.OnRefresh -> HomeEvent.Refresh
        }
    }
}