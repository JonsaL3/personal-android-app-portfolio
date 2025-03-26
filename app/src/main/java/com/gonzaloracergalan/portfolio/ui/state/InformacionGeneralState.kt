package com.gonzaloracergalan.portfolio.ui.state

import com.gonzaloracergalan.portfolio.ui.model.InformacionGeneralModel

sealed class InformacionGeneralState {
    data object Loading : InformacionGeneralState()
    data class Idle(val data: InformacionGeneralModel) : InformacionGeneralState()
    data class Error(val error: Int) : InformacionGeneralState()
}