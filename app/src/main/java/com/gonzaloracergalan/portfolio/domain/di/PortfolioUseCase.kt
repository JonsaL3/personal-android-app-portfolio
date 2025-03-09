package com.gonzaloracergalan.portfolio.domain.di

import com.gonzaloracergalan.portfolio.data.repository.util.RepositoryResponse
import org.koin.core.component.KoinComponent
import org.slf4j.LoggerFactory

abstract class PortfolioUseCase : KoinComponent {
    companion object {
        private val logger = LoggerFactory.getLogger("PortfolioUseCase")
    }

    protected fun getFlowData(repositoryResponse: RepositoryResponse) {
        logger.trace("repositoryResponseToDomainResponse")
        tengo que crear 2 funciones para los 2 casos que hay, el de setCurrentResume y el de getCurrentPerfil
    }



}