package com.bruno13palhano.home.ui.presenter

import com.bruno13palhano.ui.shared.Reducer

internal class HomeReducer : Reducer<HomeState, HomeEvent, HomeEffect> {
    override fun reduce(previousState: HomeState, event: HomeEvent): Pair<HomeState, HomeEffect?> {
        return when (event) {
            HomeEvent.Refresh -> {
                previousState.copy(loading = true) to null
            }
        }
    }
}