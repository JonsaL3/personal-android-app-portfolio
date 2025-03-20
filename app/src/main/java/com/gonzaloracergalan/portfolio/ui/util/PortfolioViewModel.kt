package com.gonzaloracergalan.portfolio.ui.util

import androidx.lifecycle.ViewModel
import com.gonzaloracergalan.portfolio.common.response.UseCaseResponse
import org.koin.core.component.KoinComponent
import org.slf4j.LoggerFactory

abstract class PortfolioViewModel : ViewModel(), KoinComponent {
    companion object {
        private val logger = LoggerFactory.getLogger("PortfolioViewModel")

        const val ERROR_CODE_BAD_REQUEST_PARAMS = -1
        const val ERROR_CODE_INTERNAL_DATABASE_ERROR = -2
        const val ERROR_CODE_INTERNAL_DATABASE_RESTRICTION = -3
        const val ERROR_CODE_RESPONSE_MAPPER_PROBLEM = -4
        const val ERROR_CODE_UNKNOWN_ERROR = -5000
    }

    protected fun getErrorCodeFromUseCaseErrorType(
        useCaseErrorType: UseCaseResponse.UseCaseErrorType
    ): Int {
        logger.trace("getErrorCodeFromUseCaseErrorType")
        val errorType = when (useCaseErrorType) {
            UseCaseResponse.UseCaseErrorType.BAD_REQUEST_PARAMS -> ERROR_CODE_BAD_REQUEST_PARAMS
            UseCaseResponse.UseCaseErrorType.INTERNAL_DATABASE_ERROR -> ERROR_CODE_INTERNAL_DATABASE_ERROR
            UseCaseResponse.UseCaseErrorType.INTERNAL_DATABASE_RESTRICTION -> ERROR_CODE_INTERNAL_DATABASE_RESTRICTION
            UseCaseResponse.UseCaseErrorType.RESPONSE_MAPPER_PROBLEM -> ERROR_CODE_RESPONSE_MAPPER_PROBLEM
            UseCaseResponse.UseCaseErrorType.UNKNOWN_ERROR -> ERROR_CODE_UNKNOWN_ERROR
        }
        logger.trace("getErrorCodeFromUseCaseErrorType: $errorType")
        return errorType
    }
}