package com.gonzaloracergalan.portfolio.ui.viewmodel

import androidx.lifecycle.viewModelScope
import com.gonzaloracergalan.portfolio.common.response.UseCaseResponse
import com.gonzaloracergalan.portfolio.domain.usecase.GetCurrentInformacionGeneralUiUseCase
import com.gonzaloracergalan.portfolio.ui.model.InformacionGeneralUI
import com.gonzaloracergalan.portfolio.ui.state.GetCurrentInformacionGeneralState
import com.gonzaloracergalan.portfolio.ui.util.PortfolioViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import org.koin.core.component.inject
import org.slf4j.LoggerFactory

class InformacionGeneralViewModel : PortfolioViewModel() {
    companion object {
        private val logger = LoggerFactory.getLogger("InformacionGeneralViewModel")
    }

    private val getCurrentInformacionGeneralUiUseCase: GetCurrentInformacionGeneralUiUseCase by inject()

    val getCurrentInformacionGeneralState: StateFlow<GetCurrentInformacionGeneralState>
        = getCurrentInformacionGeneralUiUseCase().map { useCaseFlow ->
            logger.trace("getCurrentInformacionGeneralState: {}", useCaseFlow)
            when(useCaseFlow) {
                is UseCaseResponse.Loading -> GetCurrentInformacionGeneralState.Loading
                is UseCaseResponse.NotData -> GetCurrentInformacionGeneralState.NotData
                is UseCaseResponse.Error -> {
                    GetCurrentInformacionGeneralState.Error(
                        getErrorCodeFromUseCaseErrorType(useCaseFlow.error)
                    )
                }
                is UseCaseResponse.Success<*> -> GetCurrentInformacionGeneralState.Success(
                    data = useCaseFlow.data as InformacionGeneralUI
                )
            }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = GetCurrentInformacionGeneralState.Loading
        )
}