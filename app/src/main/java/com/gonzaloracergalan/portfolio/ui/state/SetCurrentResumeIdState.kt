package com.gonzaloracergalan.portfolio.ui.state

sealed class SetCurrentResumeIdState {
    object Loading : SetCurrentResumeIdState()
    data object NotData : SetCurrentResumeIdState()
    data object Success : SetCurrentResumeIdState()
    data class Error(val error: Int) : SetCurrentResumeIdState()
}