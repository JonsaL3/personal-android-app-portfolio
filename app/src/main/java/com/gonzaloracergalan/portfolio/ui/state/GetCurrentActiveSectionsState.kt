package com.gonzaloracergalan.portfolio.ui.state

import com.gonzaloracergalan.portfolio.ui.model.ActiveResumeSectionUi

sealed class GetCurrentActiveSectionsState {
    object Loading : GetCurrentActiveSectionsState()
    // Si no hay secciones activas mostrar pantalla de insertar usuario.
    data object NotData : GetCurrentActiveSectionsState()
    data class Success(val data: Set<ActiveResumeSectionUi>) : GetCurrentActiveSectionsState()
    data class Error(val error: Int) : GetCurrentActiveSectionsState()
}