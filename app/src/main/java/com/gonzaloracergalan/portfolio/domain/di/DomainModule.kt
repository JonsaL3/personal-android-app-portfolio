package com.gonzaloracergalan.portfolio.domain.di

import com.gonzaloracergalan.portfolio.domain.usecase.GetCurrentInformacionGeneralUiUseCase
import com.gonzaloracergalan.portfolio.domain.usecase.SetCurrentResumeIdUseCase
import com.gonzaloracergalan.portfolio.common.util.PortfolioKoinModule
import com.gonzaloracergalan.portfolio.domain.usecase.GetCurrentActiveSectionsUseCase
import org.koin.core.module.Module
import org.koin.dsl.module

object DomainModule : PortfolioKoinModule {
    override val module: Module
        get() = module {
            // casos de uso
            single { GetCurrentInformacionGeneralUiUseCase() }
            single { SetCurrentResumeIdUseCase() }
            single { GetCurrentActiveSectionsUseCase() }
        }
}