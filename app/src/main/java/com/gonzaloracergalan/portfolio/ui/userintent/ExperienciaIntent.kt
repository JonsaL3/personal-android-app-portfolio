package com.gonzaloracergalan.portfolio.ui.userintent

sealed class ExperienciaIntent {
    data class OpenUrl(val url: String) : ExperienciaIntent()
}