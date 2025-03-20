package com.gonzaloracergalan.portfolio.ui.state

import com.gonzaloracergalan.portfolio.ui.model.InformacionGeneralUI

sealed class GetCurrentInformacionGeneralState {
    object Loading : GetCurrentInformacionGeneralState()
    data object NotData : GetCurrentInformacionGeneralState()
    data class Success(val data: InformacionGeneralUI) : GetCurrentInformacionGeneralState()
    data class Error(val error: Int) : GetCurrentInformacionGeneralState()
}