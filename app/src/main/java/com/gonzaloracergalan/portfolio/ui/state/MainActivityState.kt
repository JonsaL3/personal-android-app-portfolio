package com.gonzaloracergalan.portfolio.ui.state

import com.gonzaloracergalan.portfolio.ui.model.MainActivityModel

sealed class MainActivityState {
    data object Loading : MainActivityState()
    data class Idle(val data: MainActivityModel) : MainActivityState()
    data class Error(val error: Int) : MainActivityState()
}