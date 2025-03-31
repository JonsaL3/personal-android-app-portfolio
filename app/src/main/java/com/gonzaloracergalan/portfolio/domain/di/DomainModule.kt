package com.gonzaloracergalan.portfolio.domain.di

import com.gonzaloracergalan.portfolio.common.PortfolioKoinModule
import com.gonzaloracergalan.portfolio.domain.usecase.GetCurrentActiveSectionsUseCase
import com.gonzaloracergalan.portfolio.domain.usecase.GetCurrentExperienciaUseCase
import com.gonzaloracergalan.portfolio.domain.usecase.GetCurrentInformacionBasicaUseCase
import com.gonzaloracergalan.portfolio.domain.usecase.SetCurrentResumeIdUseCase
import org.koin.core.module.Module
import org.koin.dsl.module

object DomainModule : PortfolioKoinModule {
    override val module: Module
        get() = module {
            // casos de uso
            single { GetCurrentInformacionBasicaUseCase() }
            single { SetCurrentResumeIdUseCase() }
            single { GetCurrentActiveSectionsUseCase() }
            single { GetCurrentExperienciaUseCase() }
        }
}