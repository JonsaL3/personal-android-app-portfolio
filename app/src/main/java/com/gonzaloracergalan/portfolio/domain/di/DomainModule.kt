package com.gonzaloracergalan.portfolio.domain.di

import com.gonzaloracergalan.portfolio.domain.usecase.GetCurrentPerfilUiUseCase
import com.gonzaloracergalan.portfolio.util.PortfolioKoinModule
import org.koin.core.module.Module
import org.koin.dsl.module

object DomainModule : PortfolioKoinModule {
    override val module: Module
        get() = module {
            // casos de uso
            single { GetCurrentPerfilUiUseCase() }
        }
}