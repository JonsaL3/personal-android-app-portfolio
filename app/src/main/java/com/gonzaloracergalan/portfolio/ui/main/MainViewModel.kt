package com.gonzaloracergalan.portfolio.ui.main

import androidx.lifecycle.ViewModel
import com.gonzaloracergalan.portfolio.domain.usecase.GetPerfilUseCase

class MainViewModel : ViewModel() {

    init {
        GetPerfilUseCase()
    }

}