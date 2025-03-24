package com.gonzaloracergalan.portfolio.domain.usecase

import com.gonzaloracergalan.portfolio.common.response.UseCaseResponse
import com.gonzaloracergalan.portfolio.domain.usecase.GetCurrentInformacionGeneralUiUseCase.Companion
import com.gonzaloracergalan.portfolio.domain.util.PortfolioUseCase
import kotlinx.coroutines.flow.Flow
import org.slf4j.LoggerFactory

class GetCurrentActiveScreensUseCase : PortfolioUseCase() {
    companion object {
        private val logger = LoggerFactory.getLogger("GetCurrentActiveScreensUseCase")
    }

    operator fun invoke(): Flow<UseCaseResponse> {
        logger.trace("invoke")

    }
}