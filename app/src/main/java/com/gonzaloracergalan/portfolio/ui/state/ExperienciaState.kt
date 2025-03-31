package com.gonzaloracergalan.portfolio.ui.state

import com.gonzaloracergalan.portfolio.ui.model.ExperienciaModel

sealed class ExperienciaState {
    data object Loading : ExperienciaState()
    data class Idle(val data: ExperienciaModel) : ExperienciaState()
    data class Error(val error: Int) : ExperienciaState()
}